package pe.msbaek.learningtest.modulith;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.springframework.modulith.test.Scenario;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * [How to Test Spring Application Events | Baeldung](https://www.baeldung.com/spring-test-application-events)
 * allows us to write tests in a declarative way, using the Scenario API.
 * <p>
 * it makes it easy to initiate the test and evaluate its outcome by:
 * - performing method calls
 * - publishing application events
 * - verifying state changes
 * - capturing and verifying outgoing events
 * <p>
 * Additionally, the API offers a few other utilities, such as:
 * - polling and waiting for asynchronous application events
 * - defining timeouts
 * - perform filtering and mapping on the captured events
 * - creating custom assertions
 */
@ApplicationModuleTest
public class SpringModulithScenarioApiUnitTest {
    @Autowired
    OrderService orderService;

    @Autowired
    LoyalCustomersRepository loyalCustomers;

    @Test
    void whenPlacingOrder_thenPublishOrderCompletedEvent(Scenario scenario) {
        /**
         * andWaitforStateChange()
         * accepts a lambda expression and it retries executing it until it returns a non-null object or a non-empty Optional.
         * This mechanism can be particularly useful for asynchronous method calls.
         */
        scenario.stimulate(() -> orderService.placeOrder("customer-1", "product-1", "product-2"))
                .andWaitForEventOfType(OrderCompletedEvent.class)
                .toArriveAndVerify(evt -> assertThat(evt)
                        .hasFieldOrPropertyWithValue("customerId", "customer-1")
                        .hasFieldOrProperty("orderId")
                        .hasFieldOrProperty("timestamp"));
    }

    @Test
    void whenReceivingPublishOrderCompletedEvent_thenRewardCustomerWithLoyaltyPoints(Scenario scenario) {
        scenario.publish(new OrderCompletedEvent("order-1", "customer-1", Instant.now()))
                .andWaitForStateChange(() -> loyalCustomers.find("customer-1"))
                .andVerify(it -> assertThat(it)
                        .isPresent().get()
                        .hasFieldOrPropertyWithValue("customerId", "customer-1")
                        .hasFieldOrPropertyWithValue("points", 60));
    }
}