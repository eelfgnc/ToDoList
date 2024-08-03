package com.elifgenc.service.data.entity;

import com.elifgenc.service.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
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
            name = "email",
            columnDefinition = "varchar(255)",
            nullable = false,
            unique = true
    )
    private String email;

    @Column(
            name = "password",
            columnDefinition = "varchar(150)",
            nullable = false
    )
    private String password;

    @Column(
            name = "is_locked"
    )
    private boolean isLocked;

    @Column(
            name = "is_deleted"
    )
    private boolean isDeleted;

    @Column(
            name = "last_login_date"
    )
    private LocalDateTime lastLoaginDate;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserRole> userRoles= new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Task> toDoItems = new ArrayList<>();

    public String getFullName(){
        return getFirstName() + " " + getLastName();
    }
}
