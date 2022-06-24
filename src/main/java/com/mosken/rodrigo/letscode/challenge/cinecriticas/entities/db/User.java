package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db;

import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.UserException;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "User")
@Builder
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String username;

    private String password;

    private String email;

    private int xp;

    @OneToOne
    @JoinColumn(name = "role_name", referencedColumnName = "name")
    private Role role;

    public User(String username, String password, String email, int xp, Role role) {
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setXp(xp);
        setRole(role);
    }

    public void setUsername(String username) {
        validateAttribute(username, "USERNAME");
        this.username = username;
    }

    public void setPassword(String password) {
        validateAttribute(password, "PASSWORD");
        this.password = password;
    }

    public void setEmail(String email) {
        validateAttribute(email, "EMAIL");
        this.email = email;
    }

    public void setXp(int xp) {
        validateAttributeObject(xp, "XP");
        this.xp = xp;
    }

    public void setRole(Role role) {
        validateAttributeObject(role, "ROLE");
        this.role = role;
    }

    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    public static class UserBuilder {
        public User build() {
            return new User(
                    username,
                    password,
                    email,
                    xp,
                    role
            );
        }
    }
}