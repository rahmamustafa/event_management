package gov.iti.evento.services.dtos.revenue;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TotalRevenueDto {
    private String eventName;
    private Double revenue;
}
