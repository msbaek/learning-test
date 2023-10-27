package pe.msbaek.studyingtest.approvaltests;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * Demo: Leap Year Kata
 * https://www.youtube.com/watch?v=WssiXwhmtFY
 */
public class TestFirstApprovalTesting {
    record YearTuple(int year, String description) {
    }

    @Test
    void recentYears() {
        List<YearTuple> years = List.of(
                new YearTuple(2001, "typical case - divides by 4"),
                new YearTuple(1996, "typical leaf year case - divides by 4"),
                new YearTuple(1900, "atypical - divides by 100"),
                new YearTuple(2000, "atypical - divides by 400")
        );
        List<String> results = years.stream()
                .map(t -> t.year() + " -> " + isLeapYear(t.year()) + "\t" + t.description())
                .toList();
        Approvals.verify(String.join("\n", results));
    }

    private boolean isLeapYear(int year) {
        if(year % 400 == 0)
            return true;
        if(year % 100 == 0)
            return false;
        return year % 4 == 0;
    }
}