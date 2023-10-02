package pe.msbaek.studyingtest.insitu;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class Rectangle {
    private final int x;
    private final int y;
    private final int width;
    private final int height;

    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        ///
    }

    public int area() {
        return width * height;
//        throw new UnsupportedOperationException("Rectangle::area not implemented yet");
    }
}
public class RectangleTest {
    @Test
    void area() {
        Rectangle rectangle = new Rectangle(1, 1, 5, 10);
        assertThat(rectangle.area()).isEqualTo(50);
    }
}
