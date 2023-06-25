package gov.iti.evento.repositories;

import gov.iti.evento.entites.Event;
import gov.iti.evento.entites.UserInterest;
import gov.iti.evento.entites.UserInterestId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserInterestRepository extends JpaRepository<UserInterest, UserInterestId> {

    @Query("SELECT ui.event FROM UserInterest ui WHERE ui.user.id = :userId")
    public List<Event> findInterestsByUserId(Integer userId);


}