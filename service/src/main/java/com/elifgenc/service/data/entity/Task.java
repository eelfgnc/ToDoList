package com.elifgenc.service.data.entity;

import com.elifgenc.service.audit.AuditingAwareBaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Builder
@Entity
@Table(name = "task")
@NoArgsConstructor
@AllArgsConstructor
public class Task extends AuditingAwareBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "item",
            length = 600,
            nullable = false,
            columnDefinition = "varchar(600) default ''"
    )
    private String item;

    @Column(
            name = "is_done",
            columnDefinition = "boolean default false"
    )
    private Boolean isDone;

    @Column(
            name = "is_deleted",
            columnDefinition = "boolean default false"
    )
    private Boolean isDeleted;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
}
