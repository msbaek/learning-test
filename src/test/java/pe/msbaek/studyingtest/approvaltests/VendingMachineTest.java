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
        String result = act();

        Approvals.verify(result);
    }

    private String act() {
        VendingMachinePrinter vendingMachinePrinter = new VendingMachinePrinter(machine);

        StringBuilder sb  = new StringBuilder("Feature: Nickel is accepted\n\n");
        sb.append(vendingMachinePrinter.print());

        machine.insertCoin(coins.get("nickel"));
        sb.append("\ninsert coin: nickel\n\n");

        sb.append(vendingMachinePrinter.print());
        return sb.toString();
    }
}