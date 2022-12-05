package com.kruger.krugerchallenge.entity;

import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Getter
@Setter
@EqualsAndHashCode(of = "userId")
@ToString(of = "userId")
@Entity
@NoArgsConstructor
@Table(name ="users")
@Builder
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue
    @Column(name="user_id")
    private UUID userId;

    @NotNull
    @Column(unique=true)
    private String username;

    @NotNull
    private String password;

    private String fullName;

    @NotNull
    @Column(unique=true)
    private String dni;

    @ManyToMany(fetch = FetchType.EAGER)
    @Builder.Default
    @JoinTable(name = "users_roles",
            joinColumns = { @JoinColumn(name = "user_id") },
            inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private Set<Role> roles= new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "employee_id")
    @NotNull
    private Employee employee;

}
