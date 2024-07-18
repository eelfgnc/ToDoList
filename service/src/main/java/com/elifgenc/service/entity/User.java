package com.elifgenc.service.entity;


import com.elifgenc.service.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User extends AuditingAwareBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(
            name = "first_name",
            columnDefinition = "varchar(255) default ''",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            columnDefinition = "varchar(255) default ''",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "email",
            columnDefinition = "varchar(255)",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "phone",
            columnDefinition = "varchar(11)"
    )
    private String phone;

    @Column(
            name = "city",
            columnDefinition = "varchar(120)"
    )
    private String city;


    @Column(
            name = "user_name",
            columnDefinition = "varchar(150)",
            nullable = false,
            unique = true
    )
    private String userName;

    @Column(
            name = "pass",
            columnDefinition = "varchar(150)",
            nullable = false
    )
    private String pass;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles = new ArrayList<>();
}
