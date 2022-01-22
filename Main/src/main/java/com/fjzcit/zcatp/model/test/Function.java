package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 系统功能
 */

@Data
@ToString
@Entity
@Table(name = "ZCATP_FUNCTION")
@org.hibernate.annotations.Table(appliesTo = "zcatp_function", comment = "系统功能")
public class Function {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // 编号
    @Column(name = "CODE",
            nullable = false,
            columnDefinition = "VARCHAR(64) COMMENT '编号'")
    private String code;

    // 名称
    @Column(name = "NAME",
            nullable = false,
            columnDefinition = "VARCHAR(128) COMMENT '名称'")
    private String name;

    // 父节点ID
    @Column(name = "PARENT_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '父节点ID'")
    private Integer parentId;

    // 所属系统
    @Column(name = "SYSTEM_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属系统'")
    private Integer systemId;

    // 所属模块
    @Column(name = "MODULE_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属模块'")
    private Integer moduleId;

    // 状态
    @Column(name = "STATE",
            nullable = false,
            columnDefinition = "INT DEFAULT 1 COMMENT '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "REMARKS",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '备注'")
    private String remarks;
}
