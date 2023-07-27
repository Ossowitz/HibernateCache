package ru.iooko;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.iooko.entity.Birthday;
import ru.iooko.entity.Role;
import ru.iooko.entity.User;

import java.time.LocalDate;

public class HibernateRunner {

    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .registerTypeOverride(new JsonBinaryType())
                .configure()
                .addAnnotatedClass(User.class);

        try (SessionFactory sessionFactory = configuration.buildSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            User user = User.builder()
                    .username("76myxomor76@gmail.com@gmail.com")
                    .firstname("Austin")
                    .lastname("Pershing")
                    .info("""
                            {
                                "name": "Ivan",
                                "id": 25
                            }
                            """)
                    .birthdate(new Birthday(LocalDate.of(2003, 6, 5)))
                    .role(Role.ADMIN)
                    .build();
            session.persist(user);

            session.getTransaction().commit();
        }
    }
}
