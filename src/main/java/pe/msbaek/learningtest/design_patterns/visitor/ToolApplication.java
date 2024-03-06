package pe.msbaek.learningtest.design_patterns.visitor;

abstract class ResourceFile {
    protected String filePath;

    public ResourceFile(String filePath) {
        this.filePath = filePath;
    }

    public abstract void accept(Extractor extractor);
    public abstract void accept(Compressor compressor);
}

class PPTFile extends ResourceFile {
    public PPTFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }
}

class PdfFile extends ResourceFile {
    public PdfFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }
}

class WordFile extends ResourceFile {
    public WordFile(String filePath) {
        super(filePath);
    }

    @Override
    public void accept(Extractor extractor) {
        extractor.extract2txt(this);
    }

    @Override
    public void accept(Compressor compressor) {
        compressor.compress(this);
    }
}

// 실행 결과:
// Extract PDF.
// Extract WORD.
// Extract PPT.
public class ToolApplication {
}