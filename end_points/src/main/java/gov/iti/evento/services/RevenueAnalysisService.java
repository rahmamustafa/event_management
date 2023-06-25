package gov.iti.evento.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import gov.iti.evento.repositories.EventRepository;
import gov.iti.evento.repositories.UserTicketRepository;
import gov.iti.evento.services.dtos.revenue.RevenueByDateDto;
import gov.iti.evento.services.dtos.revenue.RevenueByEventTypeDto;
import gov.iti.evento.services.dtos.revenue.TotalRevenueDto;

@Service
public class RevenueAnalysisService  {

    @Autowired
    private  UserTicketRepository userTicketRepository;

    
    public List<TotalRevenueDto> calculateTotalRevenue() {
        return userTicketRepository.calculateTotalRevenue();
    }

    public List<RevenueByEventTypeDto> calculateRevenueByEventType() {
        return userTicketRepository.calculateRevenueByEventType();
    }

    public List<RevenueByDateDto> calculateRevenueByDate() {
        return userTicketRepository.calculateRevenueByDate();
    }
}