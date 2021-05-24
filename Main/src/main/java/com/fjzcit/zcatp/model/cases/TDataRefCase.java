package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "t_data_ref_case")
@org.hibernate.annotations.Table(appliesTo = "t_data_ref_case", comment = "用例数据使用情况")
public class TDataRefCase {
    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 用例类型
    @Column(name = "case_type",
            nullable = false,
            columnDefinition = "int comment '用例类型'")
    private Integer caseType;

    // 用例ID
    @Column(name = "case_id",
            nullable = false,
            columnDefinition = "int comment '用例ID'")
    private Integer caseId;

    // 数据ID
    @Column(name = "data_id",
            nullable = false,
            columnDefinition = "int comment '数据ID'"
    )
    private Integer dataId;
}
