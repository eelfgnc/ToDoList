package com.elifgenc.service.data.entity;

import com.elifgenc.service.audit.AuditingAwareBaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken extends AuditingAwareBaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "expired_date_time", nullable = false)
    private LocalDateTime expiredDateTime;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

}
