package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 系统功能
 */

@Data
@ToString
@Entity
@Table(name = "t_function")
@org.hibernate.annotations.Table(appliesTo = "t_function", comment = "系统功能")
public class TFunction {

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

    // 父节点ID
    @Column(name = "parent_id",
            nullable = false,
            columnDefinition = "int comment '父节点ID'")
    private Integer parentId;

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
