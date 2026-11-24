package final_project.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Production(String id, LocalDate date, BigDecimal targetQuantity,
                         BigDecimal actualQuantity, BigDecimal packagedQuantity,
                         BigDecimal shippedQuantity) {
}
