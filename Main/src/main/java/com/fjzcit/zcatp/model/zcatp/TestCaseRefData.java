package com.fjzcit.zcatp.model.zcatp;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "ZCATP_TEST_CASE_REF_DATA")
@org.hibernate.annotations.Table(appliesTo = "zcatp_test_case_ref_data", comment = "用例数据使用情况")
public class TestCaseRefData {
    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // 用例ID
    @Column(name = "CASE_ID",
            nullable = false,
            columnDefinition = "INT COMMENT '用例ID'")
    private Integer caseId;

    // 数据ID
    @Column(name = "DATA_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '数据ID'"
    )
    private Integer dataId;

    // 预期结果（不同用例调用同一份参数数据可能会有不同的预期结果）
    @Column(name = "EXPECTED_RESPONSE_CONTENT",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '不同用例调用同一份参数数据可能会有不同的预期结果'"
    )
    private String expectedResponseContent;


}
