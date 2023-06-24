package gov.iti.evento.services.util.converters;

import java.time.LocalDate;
import java.time.Period;

public class AgeUtils {

    public static LocalDate getStartDateForAgeRange(int age) {
        LocalDate today = LocalDate.now();
        return today.minus(Period.ofYears(age));
    }

    public static LocalDate getEndDateForAgeRange(int age) {
        LocalDate today = LocalDate.now();
        return today.minus(Period.ofYears(age + 1));
    }
}



