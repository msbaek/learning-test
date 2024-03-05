package pe.msbaek.learningtest.stream;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserTest {
    private static List<User> users;

    @BeforeAll
    static void init() {
        users = List.of(
                new User(1L, "Amirhosein", "Gharaati", "amirgh1380@gmail.com", 22, List.of("computer", "board games")),
                new User(2L, "Mohammad", "Shoja", "rezajsh@yahoo.com", 26, List.of("computer", "guitar")),
                new User(3L, "Babak", "Ahmadi", "babakahmadi@gmail.com", 33, List.of("shopping")),
                new User(2L, "Robin", "Eklund", "robin.eklund@twitter.com", 28, List.of("reading")),
                new User(5L, "Amir", "Tavakoli", "amirtvkli@gmail.com", 30, List.of("reading", "computer", "cooking")),
                new User(5L, "Farhad", "Kiani", "farhadkiani@focalpay.se", 28, List.of())
        );
    }

    @Test
    void filter_users_with_gmail_with_age_greater_than_equal_25() {
        List<User> filteredUsers = users.stream()
                .filter(user -> user.getEmail().endsWith("@gmail.com"))
                .filter(user -> user.getAge() >= 25)
                .toList();

        assertEquals(2, filteredUsers.size());
    }

    @Test
    void generate_users_fullname() {
        List<String> fullNames = users.stream()
                .map(user -> user.getFirstName() + " " + user.getLastName())
                .toList();

        assertEquals("Amirhosein Gharaati", fullNames.get(0));
    }

    @Test
    void first_person_with_age_greater_than_equal_25() {
        User user = users.stream()
                .filter(u -> u.getAge() >= 25)
                .findFirst()
                .orElseThrow();

        assertEquals("Mohammad", user.getFirstName());
    }

    @Test
    void count_computer_interest() {
        long count = users.stream()
                .flatMap(user -> user.getInterests().stream()) // Stream<String>
                .filter(interest -> interest.equals("computer")) // Stream<String>
                .count();
        assertEquals(3, count);

        count = users.stream()
                .map(user -> user.getInterests()) // Stream<List<String>>
                .filter(interests -> interests.contains("computer")) // Stream<List<String>>
                .count();
        assertEquals(3, count);
    }

    @Test
    void collect_unique_ids() {
        Set<Long> ids = users.stream()
                .map(User::getId)
                .collect(Collectors.toSet());

        Set<Long> expected = Set.of(1l, 2l, 3l, 5l);
        assertThat(expected).isEqualTo(ids);
    }

    @Test
    void group_users_by_email() {
        Map<String, Long> emailToCount = users.stream()
                .collect(
                        Collectors.groupingBy(
                                user -> user.getEmail().split("@")[1], Collectors.counting()
                        )
                );

        assertThat(emailToCount).contains(
                Map.entry("gmail.com", 3L),
                Map.entry("yahoo.com", 1L),
                Map.entry("twitter.com", 1L),
                Map.entry("focalpay.se", 1L)
        );
    }

    @Test
    void extract_all_interests() {
        Set<String> interests = users.stream()
//                .flatMap(user -> user.getInterests().stream())
                .map(User::getInterests)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());

        assertThat(interests).contains("computer", "board games", "guitar", "shopping", "reading", "cooking");
    }

    @Test
    void find_users_by_id_if_exists() {
        List<Long> ids = List.of(1L, 2L, 3L, 4L, 5L);
        List<User> foundUsers = users.stream()
                .filter(user -> ids.contains(user.getId()))
                .toList();

        assertThat(foundUsers).hasSize(6);
    }

    @Test
    void extract_duplicated_user_ids() {
        Set<Long> duplicatedIds = users.stream()
                .collect(Collectors.groupingBy(User::getId, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());

        assertThat(duplicatedIds).contains(2L, 5L);
    }
}