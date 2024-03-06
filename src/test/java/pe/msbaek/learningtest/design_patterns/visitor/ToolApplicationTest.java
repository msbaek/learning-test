package pe.msbaek.learningtest.design_patterns.visitor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class ToolApplicationTest {
    @DisplayName("3가지 타입의 리소스 파일에 대해서 extract2txt를 호출한다")
    @Test
    void main() {
        List<ResourceFile> resourceFiles = List.of(
                new PdfFile("a.pdf"),
                new WordFile("b.doc"),
                new PPTFile("c.ppt")
        );

        Extractor extractor = new Extractor();
        for (ResourceFile resourceFile : resourceFiles) {
            resourceFile.accept(extractor);
        }
    }
}