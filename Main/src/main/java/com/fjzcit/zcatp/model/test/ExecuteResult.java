package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 批次执行结果
 */

@Data
@ToString
@Entity
@Table(name = "ZCATP_EXECUTE_RESULT")
@org.hibernate.annotations.Table(appliesTo = "zcatp_execute_result", comment = "用例执行结果")
public class ExecuteResult {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // 执行批次ID
    @Column(name = "BATCH_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '执行批次ID'"
    )
    private Integer batchId;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "batch_id")
//    private TExecuteBatch executeBatch;

    // 所属迭代/增量
    @Column(name = "ITERATION_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属迭代/增量'")
    private Integer iterationId;

    // 所属系统
    @Column(name = "SYSTEM_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属系统'")
    private Integer systemId;

    // 业务类型
    @Column(name = "BIZ_TYPE",
            nullable = false,
            columnDefinition = "TINYINT COMMENT '业务类型，0：功能测试；1：接口测试；'"
    )
    private Integer bizType;

    // 用例ID
    @Column(name = "CASE_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '用例ID，包括功能测试和接口测试'"
    )
    private Integer caseId;

    // 预期响应结果类型
    @Column(name = "EXPECTED_RESPONSE_TYPE",
            nullable = true,
            columnDefinition = "TINYINT DEFAULT 0 COMMENT '预期响应结果类型，0：文本；1：json'"
    )
    private Integer expectedResponseType;

    // 预期响应内容
    @Column(name = "EXPECTED_RESPONSE_CONTENT",
            nullable = true,
            columnDefinition = "VARCHAR(1024) COMMENT '预期响应内容'"
    )
    private String expectedResponseContent;

    // 实际响应内容
    @Column(name = "ACTUAL_RESPONSE_CONTENT",
            nullable = true,
            columnDefinition = "VARCHAR(1024) COMMENT '实际响应内容'"
    )
    private String actualResponseContent;

    // 执行结果
    @Column(name = "EXECUTE_RESULT",
            nullable = true,
            columnDefinition = "TINYINT COMMENT '执行结果，0：不通过、1：通过；'"
    )
    private Integer executeResult;
}
