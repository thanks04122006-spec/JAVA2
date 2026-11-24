package final_project.view;

import final_project.domain.Production;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

public class ProductionUpdatePane extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(new String[]{
            "생산번호", "생산일", "목표 수량", "실제 생산 수량", "포장된 쿠키 수", "출고된 쿠키 수"
    }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };
    private final JTable table = new JTable(model);
    private final JLabel currentActual = new JLabel();
    private final JTextField actual = new JTextField(20);
    private final JButton updateButton = new JButton("실제 생산 수량 수정");
    private List<Production> productions = List.of();

    public ProductionUpdatePane() {
        super(new BorderLayout(8, 8));
        JPanel form = new JPanel(new GridLayout(1, 0, 8, 8));
        form.setBorder(BorderFactory.createEmptyBorder(4, 12, 12, 12));
        form.add(new JLabel("현재 실제 생산 수량"));
        form.add(currentActual);
        form.add(new JLabel("새 실제 생산 수량"));
        form.add(actual);
        form.add(updateButton);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(form, BorderLayout.SOUTH);
        table.getSelectionModel().addListSelectionListener(event -> {
            if (!event.getValueIsAdjusting()) {
                showSelection();
            }
        });
        showSelection();
    }

    public void onUpdate(Runnable action) {
        updateButton.addActionListener(event -> action.run());
    }

    public void showProductions(List<Production> productions) {
        String selectedId = productionId();
        table.clearSelection();
        this.productions = productions;
        model.setRowCount(0);
        int selectedRow = -1;
        for (Production production : productions) {
            if (production.id().equals(selectedId)) {
                selectedRow = model.getRowCount();
            }
            model.addRow(new Object[]{production.id(), production.date(), production.targetQuantity(),
                    production.actualQuantity(), production.packagedQuantity(), production.shippedQuantity()});
        }
        if (selectedRow >= 0) {
            table.setRowSelectionInterval(selectedRow, selectedRow);
        }
        showSelection();
    }

    public String productionId() {
        int row = table.getSelectedRow();
        return row < 0 ? null : productions.get(row).id();
    }

    public String actualQuantity() {
        return actual.getText();
    }

    private void showSelection() {
        int index = table.getSelectedRow();
        boolean selected = index >= 0;
        updateButton.setEnabled(selected);
        actual.setEnabled(selected);
        String quantity = selected ? productions.get(index).actualQuantity().toPlainString() : "";
        currentActual.setText(quantity);
        actual.setText(quantity);
    }
}
