package pe.msbaek.studyingtest.jpa;

import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.Mockito.verify;

@SpringBootTest
class MemberEventTest {
    @Autowired CreateMember createMember;
    @MockBean
    MemberCreatedEventListener listener;

    @Test
    void member_created() {
        ArgumentCaptor<MemberCreatedEvent> eventCaptor = ArgumentCaptor.forClass(MemberCreatedEvent.class);

        createMember.create("msbaek");

        verify(listener).handleMemberCreatedEvent(eventCaptor.capture());
        Approvals.verify(eventCaptor.getValue().toString());
    }
}