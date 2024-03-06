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
//            extractor.extract2txt(resourceFile); // compile error
            /**
             * 메소드 오버로딩을 기반으로 한 설계에 대해 설명합니다.
             * <p>
             * 이 설계 방식은 다음 두 가지 주요 개념에 기반합니다:
             * <ol>
             *     <li><b>다형성:</b> 실행 시간에 객체의 실제 타입을 가져와 실제 타입에 해당하는 메소드를 실행하는 동적 바인딩을 의미합니다.</li>
             *     <li><b>함수 오버로딩:</b> 정적 바인딩의 일종으로, 컴파일 시에는 객체의 실제 타입을 알 수 없지만 선언된 유형에 해당하는 메소드가 실행됩니다.</li>
             * </ol>
             * <p>
             * 컴파일 오류를 피하기 위해 타입 변환을 수행해야 할 수도 있습니다.
             */
            switch (resourceFile) {
                case PdfFile pdf:
                    extractor.extract2txt(pdf);
                    break;
                case WordFile word:
                    extractor.extract2txt(word);
                    break;
                case PPTFile ppt:
                    extractor.extract2txt(ppt);
                    break;
                default:
                    throw new IllegalArgumentException("Unknown type: " + resourceFile);
            }
        }
    }
}