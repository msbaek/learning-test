package pe.msbaek.studyingtest.jpa;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {
    @Container
    @ServiceConnection
    static MySQLContainer mysql = new MySQLContainer("mysql:latest");

    @Autowired MemberRepository repository;
    private Long memberId;

    @BeforeEach
    void setUp() {
        Member member = new Member();
        member.setName("Myeongseok Baek");
        repository.save(member);
        memberId = member.getId();
    }

    @Test
    void findById() {
        repository.findById(memberId).get();
        repository.findById(memberId).get();
    }
}