package com.elifgenc.service.entity;

import com.elifgenc.service.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity
@Table(name = "user_role")
public class UserRole extends AuditingAwareBaseEntity implements Serializable {
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "role_id", unique = true, nullable = false)
    private Role role;
}