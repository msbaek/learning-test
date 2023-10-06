package pe.msbaek.studyingtest.approvaltests;

import java.util.*;

import static java.awt.SystemColor.text;

public class VendingMachinePrinter {
    private final int columns;
    private final VendingMachine machine;

    public VendingMachinePrinter(VendingMachine machine) {
        this.columns = 60;
        this.machine = machine;
    }

    String print(){
        Map<String, String> fields = new LinkedHashMap();
        fields.put("Display", machine.display());
        fields.put("Balance", machine.balance().toString());
        fields.put("Coins", formatCoins(machine.coins()));

        StringBuilder lines = new StringBuilder("Vending Machine\n");
        fields.forEach(
                (key, value) -> lines.append(formatLineWithWhitespace(key, value))
        );

        return lines.toString();
    }

    private String formatCoins(Integer[] coins) {
        // join coins with commas, start with [, end with ]
        String joined = Arrays.stream(coins)
                .map(Object::toString)
                .reduce((a, b) -> a + ", " + b)
                .orElse("");
        return String.format("[%s]", joined);
    }

    /** Convenience function that lays out a name and a value at either ends of a fixed-width line.
     * eg if you call it with name="Foo" value="Bar" it will return
     * Foo                                       Bar
     */
    private String formatLineWithWhitespace(String name, String value){
        int whitespaceSize = columns - name.length() - value.length();
        return String.format("%s%s%s\n", name, " ".repeat(Math.max(0, whitespaceSize)), value);
    }
}