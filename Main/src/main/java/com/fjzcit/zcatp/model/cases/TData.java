package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "t_data")
@org.hibernate.annotations.Table(appliesTo = "t_data", comment = "用例驱动数据")
public class TData {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 名称/描述摘要
    @Column(name = "name",
            nullable = false,
            columnDefinition = "varchar(128) comment '名称/描述摘要'")
    private String name;

    // 参数数据
    @Column(name = "parameter",
            nullable = false,
            columnDefinition = "varchar(1024) comment '参数数据'")
    private String parameter;

    // 所属系统
    @Column(name = "system_id",
            nullable = false,
            columnDefinition = "int comment '所属系统'")
    private Integer systemId;

    // 所属模块
    @Column(name = "module_id",
            nullable = false,
            columnDefinition = "int comment '所属模块'")
    private Integer moduleId;

    // 所属功能
    @Column(name = "function_id",
            nullable = false,
            columnDefinition = "int comment '所属功能'")
    private Integer functionId;

    // 所属用例
    @Column(name = "case_id",
            nullable = false,
            columnDefinition = "int comment '用例ID'")
    private Integer caseId;

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
