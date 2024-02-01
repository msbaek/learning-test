package pe.msbaek.studyingtest.etc;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChristmasTreeTest {
    @DisplayName("10줄 짜리 Christmas Tree를 출력한다.")
    @Test
    void printChristmasTree() {
        ChristmasTree christmasTree = new ChristmasTree(10);
        Approvals.verify(christmasTree.print());
    }
}

class ChristmasTree {
    private final int lines;

    public ChristmasTree(int lines) {
        this.lines = lines;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < lines; i++) {
            sb.append(" ".repeat(lines - i - 1));
            sb.append("*".repeat(i * 2 + 1));
            sb.append("\n");
        }
        return sb.toString();
    }
}