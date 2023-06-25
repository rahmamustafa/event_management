package gov.iti.evento.repositories;

import gov.iti.evento.entites.UserTicket;
import gov.iti.evento.entites.UserTicketId;
import gov.iti.evento.services.dtos.revenue.RevenueByDateDto;
import gov.iti.evento.services.dtos.revenue.RevenueByEventTypeDto;
import gov.iti.evento.services.dtos.revenue.TotalRevenueDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface UserTicketRepository extends JpaRepository<UserTicket, UserTicketId> {
    @Transactional
    @Modifying
    @Query("update UserTicket u set u.quantity = u.quantity+?1 where u.id = ?2")
    int updateQuantityById(int quantity, UserTicketId id);


    public boolean existsById(UserTicketId userTicketId);

    
    // Event Revenue
    @Query("SELECT new gov.iti.evento.services.dtos.revenue.TotalRevenueDto (ut.eventTicket.event.title  , SUM(ut.eventTicket.price * ut.quantity) ) FROM UserTicket ut group by ut.eventTicket.event.id")
    public List<TotalRevenueDto> calculateTotalRevenue();

    @Query("SELECT new gov.iti.evento.services.dtos.revenue.RevenueByEventTypeDto (ut.eventTicket.event.category.type , SUM(ut.eventTicket.price * ut.quantity) )FROM UserTicket ut group by ut.eventTicket.event.category.id")
    public List<RevenueByEventTypeDto> calculateRevenueByEventType();

    @Query("SELECT new gov.iti.evento.services.dtos.revenue.RevenueByDateDto(ut.eventTicket.event.eventDate , SUM(ut.eventTicket.price * ut.quantity)) FROM UserTicket ut group by ut.eventTicket.event.eventDate")
    public List<RevenueByDateDto> calculateRevenueByDate();

}