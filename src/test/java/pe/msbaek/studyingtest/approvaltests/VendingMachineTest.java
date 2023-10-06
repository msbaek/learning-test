package pe.msbaek.studyingtest.approvaltests;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class VendingMachineTest {

    private VendingMachine machine;
    private Map<String, Integer> coins;

    @BeforeEach
    void setUp() {
        machine = new VendingMachine();
        coins = new HashMap<String, Integer>() {{
            put("penny", 1);
            put("nickel", 5);
            put("dime", 10);
            put("quarter", 25);
        }};
    }

    @Test
    public void test_accept_coins() {
        Story story = new Story(machine) {
            @Override
            void doIt(VendingMachine machine, Integer coin) {
                machine.insertCoin(coin);
            }
        };

        String result = story.act(machine, coins.get("nickel"), "Feature: Nickel is accepted", "insert coin: nickel");

        Approvals.verify(result);
    }
}