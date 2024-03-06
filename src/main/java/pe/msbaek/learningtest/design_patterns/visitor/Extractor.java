package pe.msbaek.learningtest.design_patterns.visitor;

public class Extractor {
    public void extract2txt(PPTFile pptFile) {
        System.out.println("Extract PPT.");
    }

    public void extract2txt(PdfFile pdfFile) {
        System.out.println("Extract PDF.");
    }

    public void extract2txt(WordFile wordFile) {
        System.out.println("Extract WORD.");
    }
}

/**
 * 몇가지 문제점
 * <p>
 *     <ul>
 *         <li>새로운 기능을 추가할 때마다 각각의 리소스 파일 클래스를 매번 수정해야 하기 때문에 OCP 위반</li>
 *         <li>이를 해결하기 위해</li>
 *         <ul>
 *             <li>Visitor 추상 인터페이스를 정의하고,</li>
 *             <li>리소스 파일 혁식을 구분하여 처리하는 오버로드 함수인 visit()를 리소스 파일 형식 개수만큼 정의해야 함</li>
 *         </ul
 *     </ul>
 */
class Compressor {
    public void compress(PPTFile pptFile) {
        System.out.println("Compress PPT.");
    }

    public void compress(PdfFile pdfFile) {
        System.out.println("Compress PDF.");
    }

    public void compress(WordFile wordFile) {
        System.out.println("Compress WORD.");
    }
}