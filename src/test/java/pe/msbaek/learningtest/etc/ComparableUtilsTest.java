package pe.msbaek.learningtest.etc;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.apache.commons.lang3.compare.ComparableUtils.is;

public class ComparableUtilsTest {
    @DisplayName("commons-lang3의 ComparableUtils를 사용한 비교 테스트")
    @Test
    void compare_using_comparable_utils() {
        BigDecimal valueA = new BigDecimal("1.0");
        BigDecimal valueB = new BigDecimal("2.0");

        boolean isEqual = is(valueA).equalTo(valueB);
        boolean isGreater = is(valueA).greaterThan(valueB);
        boolean isGreaterThanOrEqualTo = is(valueA).greaterThanOrEqualTo(valueB);
        boolean isLess = is(valueA).lessThan(valueB);
        boolean isLessThanOrEqualTo = is(valueA).lessThanOrEqualTo(valueB);

        String s = """
                valueA = %f, valueB = %f
                
                is(valueA).equalTo(valueB)              = %b
                is(valueA).greaterThan(valueB)          = %b
                is(valueA).greaterThanOrEqualTo(valueB) = %b
                is(valueA).lessThan(valueB)             = %b
                is(valueA).lessThanOrEqualTo(valueB)    = %b
                """.formatted(valueA, valueB, isEqual, isGreater, isGreaterThanOrEqualTo, isLess, isLessThanOrEqualTo);
        Approvals.verify(s);
    }
}