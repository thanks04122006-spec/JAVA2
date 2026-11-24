package final_project.controller;

import book_management.view.center.SetFrame;
import final_project.domain.Shipment;
import final_project.repository.CookieFactoryRepository;
import final_project.view.ProductionRegisterPane;
import final_project.view.ProductionStatusPane;
import final_project.view.ProductionUpdatePane;
import final_project.view.ShipmentManagePane;
import final_project.view.ShipmentStatusPane;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class ProductionController extends JFrame {
    private final CookieFactoryRepository repository = new CookieFactoryRepository();
    private final ProductionStatusPane statusPane = new ProductionStatusPane();
    private final ProductionRegisterPane registerPane = new ProductionRegisterPane();
    private final ProductionUpdatePane updatePane = new ProductionUpdatePane();
    private final ShipmentStatusPane shipmentStatusPane = new ShipmentStatusPane();
    private final ShipmentManagePane shipmentManagePane = new ShipmentManagePane();

    private void start() {
        JTabbedPane tabs = new JTabbedPane();
        tabs.addTab("생산 현황", statusPane);
        tabs.addTab("생산 등록", registerPane);
        tabs.addTab("생산 실적 수정", updatePane);
        tabs.addTab("출고 현황", shipmentStatusPane);
        tabs.addTab("출고 관리", shipmentManagePane);
        tabs.addChangeListener(event -> {
            if (tabs.getSelectedComponent() == statusPane) {
                runDatabaseAction(this::refreshStatus);
            } else if (tabs.getSelectedComponent() == updatePane) {
                runDatabaseAction(this::refreshUpdate);
            } else if (tabs.getSelectedComponent() == shipmentStatusPane) {
                runDatabaseAction(() -> shipmentStatusPane.showShipments(repository.findShipments()));
            } else if (tabs.getSelectedComponent() == shipmentManagePane) {
                runDatabaseAction(this::refreshShipments);
            }
        });

        statusPane.onSearch(() -> runDatabaseAction(this::refreshStatus));
        registerPane.onRegister(() -> runDatabaseAction(this::register));
        updatePane.onUpdate(() -> runDatabaseAction(this::update));
        shipmentManagePane.onRegister(() -> runDatabaseAction(() -> saveShipment(true)));
        shipmentManagePane.onUpdate(() -> runDatabaseAction(() -> saveShipment(false)));
        shipmentManagePane.onDelete(() -> runDatabaseAction(this::deleteShipment));

        add(tabs);
        SetFrame.setFrame(this, "쿠키공장 생산 관리", 900, 480);
        runDatabaseAction(this::refreshStatus);
    }

    private void refreshStatus() throws SQLException {
        statusPane.showProductions(repository.searchProductions(statusPane.searchId()));
    }

    private void refreshUpdate() throws SQLException {
        updatePane.showProductions(repository.searchProductions(null));
    }

    private void refreshShipments() throws SQLException {
        shipmentManagePane.showPackages(repository.findPackages());
    }

    private void saveShipment(boolean register) throws SQLException {
        Shipment shipment = shipmentManagePane.selectedShipment();
        LocalDate date = shipmentManagePane.enteredDate();
        if (date.isBefore(shipment.packageDate())) {
            throw new IllegalArgumentException("출고일은 포장일과 같거나 이후여야 합니다.");
        }

        boolean saved;
        try {
            saved = register ? repository.registerShipment(shipment, date)
                    : repository.updateShipment(shipment, date);
        } catch (SQLException exception) {
            if (!register || exception.getErrorCode() != 1) {
                throw exception;
            }
            showError("이미 출고 기록이 있는 포장 건입니다.");
            refreshShipments();
            return;
        }
        if (saved) {
            showMessage(register ? "출고를 등록했습니다." : "출고일을 수정했습니다.");
        } else {
            showError("선택한 포장 또는 출고 기록을 찾을 수 없습니다.");
        }
        refreshShipments();
    }

    private void deleteShipment() throws SQLException {
        Shipment shipment = shipmentManagePane.selectedShipment();
        Object[] options = {"삭제", "취소"};
        int answer = JOptionPane.showOptionDialog(this,
                shipment + "의 출고 기록을 삭제할까요?",
                "출고 기록 삭제", JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
                null, options, options[1]);
        if (answer == 0) {
            if (repository.deleteShipment(shipment)) {
                showMessage("출고 기록을 삭제했습니다.");
            } else {
                showError("선택한 출고 기록을 찾을 수 없습니다.");
            }
            refreshShipments();
        }
    }

    private void register() throws SQLException {
        String id = registerPane.productionId();
        if (id.isEmpty() || id.codePointCount(0, id.length()) > 64) {
            throw new IllegalArgumentException("생산번호를 1~64자로 입력하세요.");
        }
        LocalDate date = LocalDate.parse(registerPane.productionDate());
        BigDecimal target = quantity(registerPane.targetQuantity(), 1);
        BigDecimal actual = quantity(registerPane.actualQuantity(), 0);
        try {
            repository.registerProduction(id, date, target, actual);
        } catch (SQLException exception) {
            if (exception.getErrorCode() != 1) {
                throw exception;
            }
            showError("이미 등록된 생산번호입니다.");
            return;
        }
        registerPane.clearForm();
        statusPane.clearSearch();
        showMessage(id + " 생산 작업을 등록했습니다.");
    }

    private void update() throws SQLException {
        String id = updatePane.productionId();
        BigDecimal actual = quantity(updatePane.actualQuantity(), 0);
        if (!repository.updateActualQuantity(id, actual)) {
            showError("생산 기록을 찾을 수 없거나 입력한 수량이 포장된 쿠키 수보다 적습니다.");
            return;
        }
        showMessage(id + "의 실제 생산 수량을 수정했습니다.");
        refreshUpdate();
    }

    private BigDecimal quantity(String text, int minimum) {
        String value = text.strip();
        if (!value.matches("[0-9]{1,38}") || new BigDecimal(value).compareTo(BigDecimal.valueOf(minimum)) < 0) {
            throw new IllegalArgumentException(minimum + " 이상의 정수를 입력하세요. 최대 38자리까지 입력할 수 있습니다.");
        }
        return new BigDecimal(value);
    }

    private void runDatabaseAction(DatabaseAction action) {
        try {
            action.run();
        } catch (DateTimeParseException exception) {
            showError("올바른 날짜를 입력하세요. 예: 2026-11-24");
        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        } catch (SQLException exception) {
            exception.printStackTrace();
            showError("데이터베이스 처리 중 오류가 발생했습니다. 오류 코드: " + exception.getErrorCode());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "처리 오류", JOptionPane.ERROR_MESSAGE);
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "쿠키공장 생산 관리", JOptionPane.INFORMATION_MESSAGE);
    }

    @FunctionalInterface
    private interface DatabaseAction {
        void run() throws SQLException;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            UIManager.put("OptionPane.okButtonText", "확인");
            new ProductionController().start();
        });
    }
}
