package com.elifgenc.service.audit;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingAwareBaseEntity implements Serializable {
    private static final long serialVersionUID = 1l;

    @CreatedBy
    @Column(name= "create_user")
    private String createUser;

    @CreatedDate
    @Column(name = "create_date")
    private LocalDateTime createDate;

    @LastModifiedBy
    @Column(name= "update_user")
    private String updateUser;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;
}
