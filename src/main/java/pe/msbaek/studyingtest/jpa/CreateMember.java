package pe.msbaek.studyingtest.jpa;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateMember {
    private final MemberRepository memberRepository;

    public void create(String name) {
        Member member = new Member(name);
        memberRepository.save(member);
    }
}
