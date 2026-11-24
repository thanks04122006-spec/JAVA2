package final_project.view;

import final_project.domain.Production;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class ProductionStatusPane extends JPanel {
    private final JTextField searchField = new JTextField(16);
    private final JButton searchButton = new JButton("검색");
    private final DefaultTableModel model = new DefaultTableModel(new String[]{
            "생산번호", "생산일", "목표 수량", "실제 생산 수량",
            "포장된 쿠키 수", "출고된 쿠키 수"
    }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ProductionStatusPane() {
        super(new BorderLayout());
        JPanel toolbar = new JPanel();
        toolbar.add(new JLabel("생산번호"));
        toolbar.add(searchField);
        toolbar.add(searchButton);
        add(toolbar, BorderLayout.NORTH);
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void onSearch(Runnable action) {
        searchField.addActionListener(event -> action.run());
        searchButton.addActionListener(event -> action.run());
    }

    public String searchId() {
        String id = searchField.getText().strip();
        return id.isEmpty() ? null : id;
    }

    public void clearSearch() {
        searchField.setText("");
    }

    public void showProductions(List<Production> productions) {
        model.setRowCount(0);
        for (Production production : productions) {
            model.addRow(new Object[]{production.id(), production.date(),
                    production.targetQuantity(), production.actualQuantity(),
                    production.packagedQuantity(), production.shippedQuantity()});
        }
    }
}
