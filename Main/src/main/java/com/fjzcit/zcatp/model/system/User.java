package com.fjzcit.zcatp.model.system;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name="sys_user")
public class User {

    @Id	//主键id
    @GeneratedValue(strategy=GenerationType.IDENTITY)//主键生成策略
    @Column(name="id")//数据库字段名
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="age")
    private Integer age;

    @Column(name="address")
    private String address;

}