package final_project.view;

import final_project.domain.Shipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;

public class ShipmentManagePane extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(new String[]{
            "생산번호", "포장번호", "출고처", "배송주소", "포장일", "포장 수량", "현재 출고일"
    }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(model);
    private List<Shipment> packages = List.of();
    private final JTextField shipmentDate = new JTextField(20);
    private final JButton register = new JButton("출고 등록");
    private final JButton update = new JButton("출고일 수정");
    private final JButton delete = new JButton("출고 기록 삭제");

    public ShipmentManagePane() {
        super(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridLayout(1, 0, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(4, 12, 12, 12));
        form.add(new JLabel("출고일 (연-월-일)"));
        form.add(shipmentDate);
        form.add(register);
        form.add(update);
        form.add(delete);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(3).setPreferredWidth(200);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(form, BorderLayout.SOUTH);
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                showSelection();
            }
        });
        showSelection();
    }

    public void onRegister(Runnable action) {
        register.addActionListener(event -> action.run());
    }

    public void onUpdate(Runnable action) {
        update.addActionListener(event -> action.run());
    }

    public void onDelete(Runnable action) {
        delete.addActionListener(event -> action.run());
    }

    public Shipment selectedShipment() {
        int row = table.getSelectedRow();
        return row < 0 ? null : packages.get(row);
    }

    public LocalDate enteredDate() {
        return LocalDate.parse(shipmentDate.getText().strip());
    }

    public void showPackages(List<Shipment> shipments) {
        Shipment previous = selectedShipment();
        table.clearSelection();
        packages = shipments;
        model.setRowCount(0);
        int selectedRow = -1;
        for (Shipment shipment : shipments) {
            if (previous != null && shipment.productionId().equals(previous.productionId())
                    && shipment.packageId().compareTo(previous.packageId()) == 0) {
                selectedRow = model.getRowCount();
            }
            Object currentDate = shipment.shipmentDate() == null ? "출고 전" : shipment.shipmentDate();
            model.addRow(new Object[]{shipment.productionId(), shipment.packageId(), shipment.clientName(),
                    shipment.address(), shipment.packageDate(), shipment.quantity(), currentDate});
        }
        if (selectedRow >= 0) {
            table.setRowSelectionInterval(selectedRow, selectedRow);
        }
        showSelection();
    }

    private void showSelection() {
        Shipment shipment = selectedShipment();
        boolean selected = shipment != null;
        boolean shipped = selected && shipment.shipmentDate() != null;
        shipmentDate.setText(shipped ? shipment.shipmentDate().toString() : "");
        shipmentDate.setEnabled(selected);
        register.setEnabled(selected && !shipped);
        update.setEnabled(shipped);
        delete.setEnabled(shipped);
    }
}
