package pe.msbaek.studyingtest.jpa;

import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class MemberCreatedEventListener {
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleMemberCreatedEvent(MemberCreatedEvent event) {
        System.out.println("event = " + event);
    }
}