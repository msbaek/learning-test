package pe.msbaek.studyingtest.approvaltests;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

public class VendingMachineTest {
    private VendingMachine machine;
    private Map<String, Integer> coins;
    private Story story;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
        story = new Story(machine);
        coins = Map.of(
                "penny", 1,
                "nickel", 5,
                "dime", 10,
                "quarter", 25
        );
    }

    @Test
    public void test_accept_coins() {
        String result = story.act("Feature: Nickel is accepted", "insert coin: nickel",
                () -> machine.insertCoin(coins.get("nickel")));

        Approvals.verify(result);
    }
}