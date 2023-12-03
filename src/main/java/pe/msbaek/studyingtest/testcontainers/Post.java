package pe.msbaek.studyingtest.testcontainers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
class Post {
    @Id
    Integer id;
    Integer userId;
    @NotEmpty String title;
    @NotEmpty String body;
    @Version
    Integer version;

    public Post(Integer id) {
        this();
        this.id = id;
    }
}