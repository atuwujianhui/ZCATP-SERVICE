package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Data
@ToString
@Entity
@Table(name = "ZCATP_TEST_CASE")
@org.hibernate.annotations.Table(appliesTo = "zcatp_test_case", comment = "测试用例")
public class TestCase {

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

    // 用例类型（0、接口测试用例；1、功能测试用例；2、其他（待定）；）
    @Column(name = "CASE_TYPE",
            nullable = false,
            columnDefinition = "INT(1) COMMENT '用例类型（0、接口测试用例；1、功能测试用例；2、其他（待定）。'")
    private Integer caseType;

    // 所属迭代/增量
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITERATION_ID")
    private Iteration iteration;

    // 所属系统
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    // 创建人
    @Column(name = "CREATOR",
            nullable = false,
            columnDefinition = "INT(11) COMMENT '创建人'")
    private Integer creator;

    // 创建时间
    @Column(name = "CREATE_TIME",
            nullable = false,
            columnDefinition = "DATETIME comment '创建时间'")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    private Date createTime;

    // 排序（作用待定）
    @Column(name = "ORDER_INDEX",
            nullable = false,
            columnDefinition = "INT DEFAULT 1 COMMENT '排序。'")
    private Integer orderIndex;

    // 状态
    @Column(name = "STATE",
            nullable = false,
            columnDefinition = "INT(1) DEFAULT 1 COMMENT '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "REMARKS",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '备注'")
    private String remarks;
}
