package pe.msbaek.studyingtest.testcontainers;

import org.springframework.data.repository.ListCrudRepository;

public interface PostRepository extends ListCrudRepository<Post, Integer> {
}
