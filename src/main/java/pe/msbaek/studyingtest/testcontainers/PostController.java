package pe.msbaek.studyingtest.testcontainers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/posts")
@RestController
public class PostController {
    private final PostRepository repository;

    @GetMapping("")
    public List<Post> getPosts() {
        return repository.findAll();
    }
}