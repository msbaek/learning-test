package pe.msbaek.studyingtest.approvaltests;

public abstract class Story {
    public Story(VendingMachine machine) {
    }

    String act(VendingMachine machine, Integer coin, String featureName, String eventName) {
        VendingMachinePrinter vendingMachinePrinter = new VendingMachinePrinter(machine);
        StringBuilder sb = new StringBuilder(featureName + "\n\n");
        sb.append(vendingMachinePrinter.print());
        doIt(machine, coin);
        sb.append("\n" + eventName + "\n\n");
        sb.append(vendingMachinePrinter.print());
        return sb.toString();
    }

    abstract void doIt(VendingMachine machine, Integer coin);
}