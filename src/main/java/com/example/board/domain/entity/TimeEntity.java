package com.example.board.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass //테이블에 매핑X, 자식Entity에게 속성만 내려주기위한 애노테이션
@EntityListeners(AuditingEntityListener.class) //JPA에게 해당 Entity는 Auditing기능을 사용한다는 것을 알리는 애노테이션
public class TimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
