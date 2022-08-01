package com.mospring.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@ApiModel(description = "用户详细信息实体类")
@Data
@Entity
@Table(name = "account_detail")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE account_detail SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class AccountDetail {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("用户详细信息的ID编号")
    Integer id;

    @Column(name = "email")
    @ApiModelProperty("用户绑定的邮箱")
    String email;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @CreatedDate
    @Column(name = "create_time")
    LocalDateTime createTime;

    @Column(name = "deleted", insertable = false, columnDefinition = "int default 0")
    Integer deleted;

}
