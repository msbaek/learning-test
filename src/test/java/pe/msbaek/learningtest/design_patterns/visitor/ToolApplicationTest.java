package pe.msbaek.learningtest.design_patterns.visitor;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

class ToolApplicationTest {
    private ByteArrayOutputStream outputCaptor = new ByteArrayOutputStream();
    private PrintStream stdout;

    @BeforeEach
    void setUp() {
        // replace System.out with ByteArrayOutputStream
        stdout = System.out;
        System.setOut(new PrintStream(outputCaptor));
    }

    @AfterEach
    void tearDown() {
        // restore System.out
        System.setOut(stdout);
    }

    @DisplayName("3가지 타입의 리소스 파일에 대해서 extract2txt를 호출한다")
    @Test
    void main() {
        List<ResourceFile> resourceFiles = List.of(
                new PdfFile("a.pdf"),
                new WordFile("b.doc"),
                new PPTFile("c.ppt")
        );

        Extractor extractor = new Extractor();
        Compressor compressor = new Compressor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
            resourceFile.accept(compressor);
        }
        Approvals.verify(outputCaptor.toString());
    }
}