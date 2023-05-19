package com.legalsight.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.Instant;

@MappedSuperclass
@EqualsAndHashCode
@Data
public class BaseEntity {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @EqualsAndHashCode.Include
    @ToString.Include
    protected String id;
    @CreationTimestamp
    @Column(name = "create_time", updatable = false)
    protected Instant createTime;
    @UpdateTimestamp
    @Column(name = "modify_time")
    protected Instant modifyTime;
    @Version
    @Column(name = "version")
    protected Long version;
}
