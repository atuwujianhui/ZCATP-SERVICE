package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "ZCATP_TEST_DATA")
@org.hibernate.annotations.Table(appliesTo = "zcatp_test_data", comment = "用例驱动数据")
public class TestData {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // 名称/描述摘要
    @Column(name = "NAME",
            nullable = false,
            columnDefinition = "VARCHAR(128) COMMENT '名称/描述摘要'")
    private String name;

    // 参数数据
    @Column(name = "PARAMETER",
            nullable = false,
            columnDefinition = "VARCHAR(1024) COMMENT '参数数据'")
    private String parameter;

    // 所属系统
    @Column(name = "SYSTEM_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属系统'")
    private Integer systemId;

    // 所属模块
    @Column(name = "MODULE_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '所属模块'")
    private Integer moduleId;

    // 所属功能
    @Column(name = "FUNCTION_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '所属功能'")
    private Integer functionId;

    // 所属用例
    @Column(name = "CASE_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '用例ID'")
    private Integer caseId;

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
