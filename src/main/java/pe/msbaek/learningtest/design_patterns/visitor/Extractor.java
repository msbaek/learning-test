package pe.msbaek.learningtest.design_patterns.visitor;

/**
 *
 * 더블 디스패치
 * <p>
 *     <ul>
 *      <li>두 개의 객체 간 상호 작용이 객체의 실제 타입에 따라 어떻게 동작할지를 결정하는 데 사용됨</li>
 *      <li>메소드 오버로딩을 통해 이루어짐</li>
 *      <li>일반적인 디스패치는 오브젝트 메소드를 호출할 때 하나의 객체(호출하는 객체)에 대해서만 동작함.</li>
 *      <li>즉, 메소드는 호출하는 객체의 타입에 따라 결정됨</li>
 *      <li>더블 디스패치는 두 객체의 타입을 모두 고려함. 즉, 메소드는 호출하는 객체와 메소드에 전달된 인수의 타입에 따라 결정됨</li>
 *     </ul>
 */
interface Visitor {
    void visit(PPTFile pptFile);

    void visit(PdfFile pdfFile);

    void visit(WordFile wordFile);
}

public class Extractor implements Visitor {
    @Override
    public void visit(PPTFile pptFile) {
        System.out.println("Extract PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Extract PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        System.out.println("Extract WORD.");
    }
}

class Compressor implements Visitor {
    @Override
    public void visit(PPTFile pptFile) {
        System.out.println("Compress PPT.");
    }

    @Override
    public void visit(PdfFile pdfFile) {
        System.out.println("Compress PDF.");
    }

    @Override
    public void visit(WordFile wordFile) {
        System.out.println("Compress WORD.");
    }
}