package ru.kata.spring.boot_security.demo.models;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "user")
public class User  {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 30, message = "Имя должно быть от 2 до 30 символов длиной")
    @Column(name = "name")
    private String name;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Size(min = 2, max = 40, message = "Фамилия должна быть от 2 до 40 символов длиной")
    @Column(name = "last_name")
    private String lastName;

    @Min(value = 1, message = "Минимальный возраст не может быть меньше 1 года!")
    @Max(value = 120, message = "Максимальный возраст не может быть больше 120 лет!")
    @Column(name = "age")
    private Byte age;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Email(message = "Неверно заполненное поле")
    @Column(name = "email", unique = true)
    private String email;

    @Size(min = 3, message = "Пароль должен содержать не менее 3-х символов")
    @Column(name = "password")
    private String password;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
    public User() {

    }

    public User(Long id, String name, String lastName, Byte age, String email, String password, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public Byte getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public User setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public User setAge(Byte age) {
        this.age = age;
        return this;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public User setRoles(Set<Role> roles) {
        this.roles = roles;
        return this;
    }





    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", age=").append(age);
        sb.append(", email='").append(email).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append(", roles=").append(roles);
        sb.append('}');
        return sb.toString();
    }
}
