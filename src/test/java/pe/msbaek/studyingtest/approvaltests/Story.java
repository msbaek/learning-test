package pe.msbaek.studyingtest.approvaltests;

public class Story {
    private final VendingMachine machine;
    private final VendingMachinePrinter printer;

    public Story(VendingMachine machine) {
        this.machine = machine;
        this.printer = new VendingMachinePrinter(machine);
    }

    String act(String featureName, String eventName, Runnable act) {
        String lines = header(featureName);
        lines += beforeOrAfter();

        act.run();

        lines += eventName(eventName);
        lines += beforeOrAfter();
        return lines;
    }

    private String eventName(String eventName) {
        return "\n" + eventName + "\n\n";
    }

    private String beforeOrAfter() {
        return printer.print();
    }

    private String header(String featureName) {
        return featureName + "\n\n";
    }
}