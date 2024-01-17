package pe.msbaek.studyingtest.tdd;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class Warehouse {
    private ConcurrentHashMap<String, Integer> products = new ConcurrentHashMap<>();

    public Warehouse(Map<String, Integer> stocks) {
        products.putAll(stocks);
    }

    int decrease(String productName, int quantity) {
        return products.compute(productName, (k, v) -> v - quantity);
    }
}

class Order {
    private final String productName;
    private final int quantity;
    private boolean filled;

    public Order(String productName, int quantity) {
        this.productName = productName;
        this.quantity = quantity;
    }

    public void fill(Warehouse warehouse) {
        warehouse.decrease(productName, quantity);
        this.filled = true;
    }

    public boolean isFilled() {
        return this.filled;
    }
}

@ExtendWith(MockitoExtension.class)
class MockSpyArgumentCaptorTest {
    @Captor
    private ArgumentCaptor<String> productNameCaptor;
    @Captor
    private ArgumentCaptor<Integer> quantityCaptor;

    @Test
    void test_with_mock() {
        Warehouse warehouse = mock(Warehouse.class);

        Order order = new Order("아메리카노", 10);
        order.fill(warehouse);

        assertThat(order.isFilled()).isTrue();
        verify(warehouse).decrease("아메리카노", 10);
    }

    @Test
    void test_with_spying() {
        Warehouse warehouse = new Warehouse(
                Map.of(
                        "아메리카노", 10,
                        "카페라떼", 10,
                        "카푸치노", 10
                )
        );
        warehouse = spy(warehouse);

        Order order = new Order("아메리카노", 10);
        order.fill(warehouse);

        assertThat(order.isFilled()).isTrue();
        verify(warehouse).decrease("아메리카노", 10);
    }

    @Test
    void test_with_argumentCaptor() {
        Warehouse warehouse = mock(Warehouse.class);

        Order order = new Order("아메리카노", 10);
        order.fill(warehouse);

        assertThat(order.isFilled()).isTrue();
        verify(warehouse).decrease(productNameCaptor.capture(), quantityCaptor.capture());
        assertThat(productNameCaptor.getValue()).isEqualTo("아메리카노");
        assertThat(quantityCaptor.getValue()).isEqualTo(10);
    }
}