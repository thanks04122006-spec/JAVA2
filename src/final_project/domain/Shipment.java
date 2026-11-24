package final_project.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Shipment(String productionId, BigDecimal packageId, String clientName,
                       String address, LocalDate packageDate, LocalDate shipmentDate,
                       BigDecimal quantity) {
    @Override
    public String toString() {
        return productionId + " / " + packageId.toPlainString() + " / " + clientName;
    }
}
