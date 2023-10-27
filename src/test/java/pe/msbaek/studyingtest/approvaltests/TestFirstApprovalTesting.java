package pe.msbaek.studyingtest.approvaltests;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

/**
 * Demo: Leap Year Kata
 * https://www.youtube.com/watch?v=WssiXwhmtFY
 */
public class TestFirstApprovalTesting {
    @Test
    void name() {
        Approvals.verify("Hello world");
    }
}