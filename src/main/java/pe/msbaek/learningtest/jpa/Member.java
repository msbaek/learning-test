package pe.msbaek.learningtest.jpa;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.AbstractAggregateRoot;

@Setter
@Getter
@ToString
@EqualsAndHashCode(of = "id", callSuper = true)
@Entity
public class Member extends AbstractAggregateRoot<Member> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    protected Member() {
    }

    public Member(String name) {
        this.name = name;
        registerEvent(new MemberCreatedEvent(name));
    }
}