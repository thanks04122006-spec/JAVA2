package final_project.repository;

import final_project.domain.Production;
import final_project.domain.Shipment;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CookieFactoryRepository {
    private static final String SHIPMENT_KEY_CONDITION = " WHERE SHP_prod_id = ? AND SHP_pack_id = ?";

    public List<Production> searchProductions(String id) throws SQLException {
        String sql = """
                SELECT p.PRD_id, p.PRD_date, p.PRD_target_qty, p.PRD_actual_qty,
                       NVL(t.packaged_qty, 0), NVL(t.shipped_qty, 0)
                FROM Production p
                LEFT JOIN (
                    SELECT k.PKG_prod_id,
                           SUM(k.PKG_unit_qty * k.PKG_qty) AS packaged_qty,
                           SUM(k.PKG_unit_qty * NVL(s.SHP_qty, 0)) AS shipped_qty
                    FROM Packaging k
                    LEFT JOIN Shipment s
                      ON s.SHP_prod_id = k.PKG_prod_id AND s.SHP_pack_id = k.PKG_id
                    GROUP BY k.PKG_prod_id
                ) t ON t.PKG_prod_id = p.PRD_id
                WHERE ? IS NULL OR INSTR(UPPER(p.PRD_id), UPPER(?)) > 0
                ORDER BY p.PRD_date DESC, p.PRD_id
                """;
        List<Production> productions = new ArrayList<>();
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setString(2, id);
            try (var rows = statement.executeQuery()) {
                while (rows.next()) {
                    productions.add(new Production(rows.getString(1), rows.getDate(2).toLocalDate(),
                            rows.getBigDecimal(3), rows.getBigDecimal(4),
                            rows.getBigDecimal(5), rows.getBigDecimal(6)));
                }
            }
        }
        return productions;
    }

    public void registerProduction(String id, LocalDate date, BigDecimal targetQuantity,
                       BigDecimal actualQuantity) throws SQLException {
        String sql = """
                INSERT INTO Production (PRD_id, PRD_date, PRD_target_qty, PRD_actual_qty)
                VALUES (?, ?, ?, ?)
                """;
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setString(1, id);
            statement.setDate(2, Date.valueOf(date));
            statement.setBigDecimal(3, targetQuantity);
            statement.setBigDecimal(4, actualQuantity);
            statement.executeUpdate();
        }
    }

    public boolean updateActualQuantity(String id, BigDecimal quantity) throws SQLException {
        String sql = """
                UPDATE Production p
                SET PRD_actual_qty = ?
                WHERE p.PRD_id = ?
                  AND ? >= (SELECT NVL(SUM(k.PKG_unit_qty * k.PKG_qty), 0)
                            FROM Packaging k WHERE k.PKG_prod_id = p.PRD_id)
                """;
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement(sql)) {
            statement.setBigDecimal(1, quantity);
            statement.setString(2, id);
            statement.setBigDecimal(3, quantity);
            return statement.executeUpdate() == 1;
        }
    }

    public List<Shipment> findShipments() throws SQLException {
        String sql = """
                SELECT s.SHP_prod_id, s.SHP_pack_id, c.CLI_name, c.CLI_address,
                       s.SHP_pack_date, s.SHP_date, s.SHP_qty
                FROM Shipment s
                JOIN Client c ON c.CLI_id = s.SHP_client_id
                ORDER BY s.SHP_prod_id, s.SHP_pack_id
                """;
        return readShipments(sql);
    }

    public List<Shipment> findPackages() throws SQLException {
        String sql = """
                SELECT p.PKG_prod_id, p.PKG_id, c.CLI_name, c.CLI_address,
                       p.PKG_date, s.SHP_date, NVL(s.SHP_qty, p.PKG_qty)
                FROM Packaging p
                JOIN Client c ON c.CLI_id = p.PKG_client_id
                LEFT JOIN Shipment s
                  ON s.SHP_prod_id = p.PKG_prod_id AND s.SHP_pack_id = p.PKG_id
                ORDER BY p.PKG_prod_id, p.PKG_id
                """;
        return readShipments(sql);
    }

    public boolean registerShipment(Shipment shipment, LocalDate date) throws SQLException {
        return executeShipmentChange("""
                INSERT INTO Shipment (SHP_prod_id, SHP_pack_id, SHP_client_id, SHP_pack_date, SHP_date, SHP_qty)
                SELECT PKG_prod_id, PKG_id, PKG_client_id, PKG_date, ?, PKG_qty
                FROM Packaging WHERE PKG_prod_id = ? AND PKG_id = ?
                """, shipment, date);
    }

    public boolean updateShipment(Shipment shipment, LocalDate date) throws SQLException {
        return executeShipmentChange("UPDATE Shipment SET SHP_date = ?" + SHIPMENT_KEY_CONDITION, shipment, date);
    }

    public boolean deleteShipment(Shipment shipment) throws SQLException {
        return executeShipmentChange("DELETE FROM Shipment" + SHIPMENT_KEY_CONDITION, shipment, null);
    }

    private boolean executeShipmentChange(String sql, Shipment shipment, LocalDate date) throws SQLException {
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement(sql)) {
            int index = 1;
            if (date != null) {
                statement.setDate(index++, Date.valueOf(date));
            }
            statement.setString(index++, shipment.productionId());
            statement.setBigDecimal(index, shipment.packageId());
            return statement.executeUpdate() == 1;
        }
    }

    private List<Shipment> readShipments(String sql) throws SQLException {
        List<Shipment> shipments = new ArrayList<>();
        try (var connection = JDBCConnector.getConnection();
             var statement = connection.prepareStatement(sql);
             var rows = statement.executeQuery()) {
            while (rows.next()) {
                Date shipmentDate = rows.getDate(6);
                shipments.add(new Shipment(rows.getString(1), rows.getBigDecimal(2),
                        rows.getString(3), rows.getString(4), rows.getDate(5).toLocalDate(),
                        shipmentDate == null ? null : shipmentDate.toLocalDate(), rows.getBigDecimal(7)));
            }
        }
        return shipments;
    }
}
