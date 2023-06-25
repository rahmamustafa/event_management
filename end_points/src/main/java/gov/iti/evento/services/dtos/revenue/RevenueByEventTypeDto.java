package gov.iti.evento.services.dtos.revenue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RevenueByEventTypeDto {
    private String type;
    private Double revenue;
}
