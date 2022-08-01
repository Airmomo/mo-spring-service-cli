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

@ApiModel(description = "用户基础信息实体类")
@Data
@Entity
@Table(name = "account")
@EntityListeners(AuditingEntityListener.class)
@SQLDelete(sql = "UPDATE account SET deleted = 1 WHERE id = ?")
@Where(clause = "deleted = 0")
public class Account {

    @Id
    @Column(name = "username",unique = true)
    @ApiModelProperty("用户名")
    String username;

    @Column(name = "password")
    @ApiModelProperty("用户密码")
    String password;

    @Column(name = "role")
    @ApiModelProperty("用户角色")
    String role;

    // 外键
    @JoinColumn(name = "detail_id")
    // fetch:设置懒加载 cascade:设置对该表的所有操作都进行关联操作
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ApiModelProperty("用户的详细信息")
    AccountDetail accountDetail;

    @LastModifiedDate
    @Column(name = "update_time")
    LocalDateTime updateTime;

    @CreatedDate
    @Column(name = "create_time")
    LocalDateTime createTime;

    @Column(name = "deleted", insertable = false, columnDefinition = "int default 0")
    Integer deleted;

}
