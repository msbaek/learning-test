package pe.msbaek.learningtest.tdd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class ReverseLinesTest {
    private final List<String> lines = List.of(
            "Presenting TDD - Mockist",
            "Presenting TDD",
            "레거시코드에 테스트 추가하는 또 하나의 방법 - Subclass and Override Method",
            "Refactoring - Functional Core & Mutable Shell",
            "Refactoring - Humble Object Pattern",
            "Refactoring - Split Phase",
            "Refactoring - Lift up conditionals",
            "레거시코드에 테스트 추가를 위한 3가지 기법",
            "OKKY 1월 세미나 한번 듣고 평생 써먹는 코드 리뷰 노하우",
            "atdd by example",
            "Outside In TDD, ATDD를 예제를 통해서 알아봅니다.",
            "Test And Test Doubles",
            "reduce dependency using introduce parameter",
            "CodeReview에대해",
            "클린 코더스 강의 18. Transformation Priority Premise",
            "클린 코더스 강의 17.1 test process",
            "클린 코더스 강의 16.2 advanced tdd 2 - prime factors",
            "클린 코더스 강의 16.1 advanced tdd 1 - name inverter",
            "expenses refactoring",
            "클린 코더스 강의 15.2. SOLID Case Study",
            "클린 코더스 강의 15.1. DIP(Dependency Inversion Principle)",
            "클린 코더스 강의 14.3. ISP(Interface Segregation Principle)",
            "클린 코더스 강의 14.2. LSP(Liskov Substitution Principle)",
            "클린 코더스 강의 14.1. OCP(Open-Closed Principle)",
            "클린 코더스 강의 13. SRP(Single Responsibility Principle)",
            "클린 코더스 강의 12. SOLID Foundation",
            "클린 코더스 강의 11. Architecture UseCase",
            "클린 코더스 강의 10. Architecture",
            "클린 코더스 강의 9. TDD 4 - primefactors and wordwrap",
            "클린 코더스 강의 8. TDD 3 - bowling game",
            "클린 코더스 강의 7. TDD 2 - tyrant",
            "클린 코더스 강의 7. TDD 1",
            "클린 코더스 강의 6. Form",
            "클린 코더스 강의 5. Function Structure Part2",
            "클린 코더스 강의 5. Function Structure",
            "클린 코더스 강의 4. Function Part2",
            "클린 코더스 강의 3. Function",
            "클린 코더스 강의 2. OOP Part2",
            "클린 코더스 강의 1. 소개 및 OOP"
    );

    @Test
    @DisplayName("""
            리스트에
            --
            forth line
            third line
            second line
            first line
            --
            위와 같이 들어 있을 때
            --
            first line
            second line
            third line
            forth line
            --
            와 같이 역순으로 반환한다
            """)
    void reverse_lines() {
        List<String> reversedLines = lines.stream()
                .sorted((line1, line2) -> -1)
                .collect(Collectors.toList());

        reversedLines.forEach(System.out::println);
    }
}