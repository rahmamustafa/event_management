package gov.iti.evento.repositories;

import gov.iti.evento.entites.UserTicket;
import gov.iti.evento.entites.UserTicketId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserTicketRepository extends JpaRepository<UserTicket, UserTicketId> {
    @Transactional
    @Modifying
    @Query("update UserTicket u set u.quantity = u.quantity+?1 where u.id = ?2")
    int updateQuantityById(int quantity, UserTicketId id);


    public boolean existsById(UserTicketId userTicketId);


}