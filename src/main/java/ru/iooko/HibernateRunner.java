package ru.iooko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.iooko.entity.User;
import ru.iooko.util.HibernateUtil;

public class HibernateRunner {

    public static void main(String[] args) {
        // Transient state
        User user = User.builder()
                .username("ivan@gmail.com")
                .firstname("Ivan")
                .lastname("Ivanov")
                .build();
        try (SessionFactory sessionFactory = HibernateUtil.buildSessionFactory()) {
            try (Session session1 = sessionFactory.openSession()) {
                session1.beginTransaction();

                // Persistent state about session1 and Transient state about session2
                session1.saveOrUpdate(user);

                session1.beginTransaction().commit();
            }
            try (Session session2 = sessionFactory.openSession()) {
                session2.beginTransaction();

                user.setFirstname("Sveta");

//                session2.delete(user);
//                session2.refresh(user);
                session2.merge(user);

                session2.beginTransaction().commit();
            }
        }
    }
}
