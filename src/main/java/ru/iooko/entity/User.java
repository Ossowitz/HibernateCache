package ru.iooko.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import ru.iooko.converter.BirthdayConverter;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    private String username;

    private String firstname;

    private String lastname;

    @Column(name = "birth_date")
    private Birthday birthdate;

    @Type(type = "com.vladmihalcea.hibernate.type.json.JsonBinaryType")
    private String info;

    @Enumerated(EnumType.STRING)
    private Role role;
}
