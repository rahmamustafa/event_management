package gov.iti.evento.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gov.iti.evento.services.RevenueAnalysisService;
import gov.iti.evento.services.dtos.revenue.RevenueByDateDto;
import gov.iti.evento.services.dtos.revenue.RevenueByEventTypeDto;
import gov.iti.evento.services.dtos.revenue.TotalRevenueDto;

@RestController
@RequestMapping("/revenue")
// @RequestMapping("/api/admin/revenue")
public class RevenueAnalysisController {
     private final RevenueAnalysisService revenueAnalysisService;

    public RevenueAnalysisController(RevenueAnalysisService revenueAnalysisService) {
        this.revenueAnalysisService = revenueAnalysisService;
    }

    @GetMapping("/total")
    public ResponseEntity<List<TotalRevenueDto>> getTotalRevenue() {
        revenueAnalysisService.calculateTotalRevenue()
                .forEach(entry -> System.out.println("Key: "+entry));
        List<TotalRevenueDto> revenueDtos =   revenueAnalysisService.calculateTotalRevenue();     
        return  ResponseEntity.ok(revenueDtos);
    }

    @GetMapping("/byEventType")
    public List<RevenueByEventTypeDto> getRevenueByEventType() {
        return revenueAnalysisService.calculateRevenueByEventType();
    }

    @GetMapping("/byDate")
    public List<RevenueByDateDto> getRevenueByDate() {
        return revenueAnalysisService.calculateRevenueByDate();
    }
}
