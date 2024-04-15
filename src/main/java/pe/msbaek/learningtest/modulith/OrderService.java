package pe.msbaek.learningtest.modulith;

import org.springframework.context.event.EventListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
class OrderService {
    private final OrderRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public OrderService(OrderRepository orders, ApplicationEventPublisher eventsPublisher) {
        this.repository = orders;
        this.eventPublisher = eventsPublisher;
    }

    public void placeOrder(String customerId, String... productIds) {
        Order order = new Order(customerId, Arrays.asList(productIds));
        // business logic to validate and place the order

        Order savedOrder = repository.save(order);

        OrderCompletedEvent event = new OrderCompletedEvent(savedOrder.id(), savedOrder.customerId(), savedOrder.timestamp());
        eventPublisher.publishEvent(event);
    }
}

@Repository
class OrderRepository {
    private AtomicLong orderIdSequence = new AtomicLong(1);
    private Map<String, Order> orderMap = new HashMap<>();

    Order save(Order order) {
        orderMap.put(order.id(), order);
        return order;
    }
}

class Order {
    private String id;
    private String customerId;
    private final List<String> productIds;
    private Instant timestamp;

    public String id() {
        return id;
    }

    public String customerId() {
        return customerId;
    }

    public Instant timestamp() {
        return timestamp;
    }

    public Order(String customerId, List<String> productIds) {
        this.customerId = customerId;
        this.productIds = productIds;
    }
}

record OrderCompletedEvent(String orderId, String customerId, Instant timestamp) {
}
@Component
class LoyalCustomersRepository {

    private List<LoyalCustomer> customers = new ArrayList<>();

    public Optional<LoyalCustomer> find(String customerId) {
        return customers.stream()
                .filter(it -> it.customerId()
                        .equals(customerId))
                .findFirst();
    }

    public void awardPoints(String customerId, int points) {
        var customer = find(customerId).orElseGet(() -> save(new LoyalCustomer(customerId, 0)));

        customers.remove(customer);
        customers.add(customer.addPoints(points));
    }

    public LoyalCustomer save(LoyalCustomer customer) {
        customers.add(customer);
        return customer;
    }

    public boolean isLoyalCustomer(String customerId) {
        return find(customerId).isPresent();
    }

    public record LoyalCustomer(String customerId, int points) {

        LoyalCustomer addPoints(int points) {
            return new LoyalCustomer(customerId, this.points() + points);
        }
    }
}

@Service
class LoyaltyPointsService {

    public static final int ORDER_COMPLETED_POINTS = 60;
    private final LoyalCustomersRepository loyalCustomers;

    public LoyaltyPointsService(LoyalCustomersRepository loyalCustomers) {
        this.loyalCustomers = loyalCustomers;
    }

    @EventListener
    public void onOrderCompleted(OrderCompletedEvent event) {
        // business logic to award points to loyal customers
        loyalCustomers.awardPoints(event.customerId(), ORDER_COMPLETED_POINTS);
    }

}