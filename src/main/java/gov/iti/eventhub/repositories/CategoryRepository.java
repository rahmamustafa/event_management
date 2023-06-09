package gov.iti.eventhub.repositories;

import gov.iti.eventhub.entites.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}