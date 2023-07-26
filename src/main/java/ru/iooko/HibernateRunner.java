package ru.iooko;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.iooko.entity.User;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration().configure()
                .addAnnotatedClass(User.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("76myxomor76@gmail.com@gmail.com")
                    .firstname("Austin")
                    .lastname("Pershing")
                    .birthDate(LocalDate.of(2003, 6, 5))
                    .age(20)
                    .build();
            session.persist(user);

            session.getTransaction().commit();
        }
    }
}
