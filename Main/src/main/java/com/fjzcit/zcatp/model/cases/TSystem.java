package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

//TODO：后续再扩展版本/增量/分支管理

/**
 * 系统
 */

@Data
@ToString
@Entity
@Table(name = "t_system")
@org.hibernate.annotations.Table(appliesTo = "t_system", comment = "系统")
public class TSystem {
    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 编号
    @Column(name = "code",
            nullable = false,
            columnDefinition = "varchar(64) comment '编号'")
    private String code;

    // 名称
    @Column(name = "name",
            nullable = false,
            columnDefinition = "varchar(128) comment '名称'")
    private String name;

    // 状态
    @Column(name = "state",
            nullable = false,
            columnDefinition = "int default 0 comment '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "remarks",
            nullable = true,
            columnDefinition = "varchar(2048) comment '备注'")
    private String remarks;
}
