package com.fjzcit.zcatp.model.zcatp;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

/**
 * 执行批次，对应迭代/增量
 */

@Data
@ToString
@Entity
@Table(name = "ZCATP_EXECUTE_BATCH")
@org.hibernate.annotations.Table(appliesTo = "zcatp_execute_batch", comment = "执行批次")
public class ExecuteBatch {

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

    // 所属迭代/增量
//    @Column(name = "iteration_id",
//            nullable = false,
//            columnDefinition = "int comment '所属迭代/增量'")
//    private Integer iterationId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITERATION_ID")
    private Iteration iteration;

    // 所属系统
    @Column(name = "SYSTEM_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '所属系统'")
    private Integer systemId;

    // 创建时间
    @CreatedDate
    private Long createTime;

    // 备注
    @Column(name = "REMARKS",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '备注'")
    private String remarks;
}
