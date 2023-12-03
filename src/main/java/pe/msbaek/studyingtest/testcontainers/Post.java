package pe.msbaek.studyingtest.testcontainers;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
class Post {
        @Id Integer id;
        Integer userId;
        @NotEmpty String title;
        @NotEmpty String body;
        @Version Integer version;
}