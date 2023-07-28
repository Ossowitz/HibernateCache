package ru.iooko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.iooko.entity.PersonalInfo;
import ru.iooko.entity.User;
import ru.iooko.util.HibernateUtil;

public class HibernateRunner {

    private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

    public static void main(String[] args) {
        // Transient state
        User user = User.builder()
                .username("petr@gmail.com")
                .personalInfo(PersonalInfo.builder()
                        .firstname("Petr")
                        .lastname("Petrov")
                        .build())
                .build();
        log.info("User entity is in transient state, object: {}", user);

        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            Session session1 = sessionFactory.openSession();
            try (session1) {
                Transaction transaction = session1.beginTransaction();
                log.trace("Trace is created,  {}", transaction);

                // Persistent state about session1 and Transient state about session2
                session1.saveOrUpdate(user);
                log.trace("User is in persistent state: {}, session {}", user, session1);

                transaction.commit();
            }
            log.warn("User is in detached state: {}, session is closed {}", user, session1);
        } catch (Exception exception) {
            log.error("Exception occurred", exception);
            throw exception;
        }
    }
}
