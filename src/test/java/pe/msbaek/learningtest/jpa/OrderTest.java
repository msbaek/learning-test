package pe.msbaek.learningtest.jpa;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@Transactional
@SpringBootTest
class OrderTest {
    @Autowired private EntityManager entityManager;

    @Test
    void refresh() {
        Order order = new Order("order1");
        entityManager.persist(order);
    }
}