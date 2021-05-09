package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 批次执行结果
 */

@Data
@ToString
@Entity
@Table(name = "t_execute_result")
@org.hibernate.annotations.Table(appliesTo = "t_execute_result", comment = "用例执行结果")
public class TExecuteResult {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 执行批次ID
    @Column(name = "batch_id",
            nullable = false,
            columnDefinition = "int comment '执行批次ID'"
    )
    private Integer batchId;
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "batch_id")
//    private TExecuteBatch executeBatch;

    // 所属迭代/增量
    @Column(name = "iteration_id",
            nullable = false,
            columnDefinition = "int comment '所属迭代/增量'")
    private Integer iterationId;

    // 所属系统
    @Column(name = "system_id",
            nullable = false,
            columnDefinition = "int comment '所属系统'")
    private Integer systemId;

    // 业务类型
    @Column(name = "biz_type",
            nullable = false,
            columnDefinition = "tinyint comment '业务类型，0：功能测试；1：接口测试；'"
    )
    private Integer bizType;

    // 用例ID
    @Column(name = "case_id",
            nullable = false,
            columnDefinition = "int comment '用例ID，包括功能测试和接口测试'"
    )
    private Integer caseId;

    // 预期响应结果类型
    @Column(name = "expected_response_type",
            nullable = true,
            columnDefinition = "tinyint default 0 comment '预期响应结果类型，0：文本；1：json'"
    )
    private String expectedResponseType;

    // 预期响应内容
    @Column(name = "expected_response_content",
            nullable = true,
            columnDefinition = "varchar(1024) comment '预期响应内容'"
    )
    private String expectedResponseContent;

    // 实际响应内容
    @Column(name = "actual_response_content",
            nullable = true,
            columnDefinition = "varchar(1024) comment '实际响应内容'"
    )
    private String actualResponseContent;

    // 执行结果
    @Column(name = "execute_result",
            nullable = true,
            columnDefinition = "tinyint comment '执行结果，0：不通过、1：通过；'"
    )
    private Integer executeResult;
}
