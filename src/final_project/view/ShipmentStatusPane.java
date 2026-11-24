package final_project.view;

import final_project.domain.Shipment;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.util.List;

public class ShipmentStatusPane extends JPanel {
    private final DefaultTableModel model = new DefaultTableModel(new String[]{
            "생산번호", "포장번호", "출고처", "배송주소", "출고일", "출고 포장 수량"
    }, 0) {
        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }
    };

    public ShipmentStatusPane() {
        super(new BorderLayout());
        JTable table = new JTable(model);
        table.setFillsViewportHeight(true);
        table.getColumnModel().getColumn(3).setPreferredWidth(240);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void showShipments(List<Shipment> shipments) {
        model.setRowCount(0);
        for (Shipment shipment : shipments) {
            model.addRow(new Object[]{shipment.productionId(), shipment.packageId(), shipment.clientName(),
                    shipment.address(), shipment.shipmentDate(), shipment.quantity()});
        }
    }
}
