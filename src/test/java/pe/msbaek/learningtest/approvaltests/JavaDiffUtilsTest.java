package pe.msbaek.learningtest.approvaltests;

import com.github.difflib.text.DiffRow;
import com.github.difflib.text.DiffRowGenerator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class JavaDiffUtilsTest {
    @Test
    void  one_liner_including_all_difference_information() {
        // create a configured DiffRowGenerator
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .mergeOriginalRevised(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")      // introduce markdown style for strikethrough
                .newTag(f -> "**")     // introduce markdown style for bold
                .build();

        // compute the differences for two test texts.
        List<DiffRow> rows = generator.generateDiffRows(
                Arrays.asList("This is a test senctence."),
                Arrays.asList("This is a test for diffutils."));

        System.out.println(rows.get(0).getOldLine());
    }

    @Test
    void side_by_side() {
        DiffRowGenerator generator = DiffRowGenerator.create()
                .showInlineDiffs(true)
                .inlineDiffByWord(true)
                .oldTag(f -> "~")
                .newTag(f -> "**")
                .build();
        List<DiffRow> rows = generator.generateDiffRows(
                Arrays.asList("This is a test senctence.", "This is the second line.", "And here is the finish."),
                Arrays.asList("This is a test for diffutils.", "This is the second line."));

        System.out.println("|original|new|");
        System.out.println("|--------|---|");
        for (DiffRow row : rows) {
            System.out.println("|" + row.getOldLine() + "|" + row.getNewLine() + "|");
        }
    }
}
