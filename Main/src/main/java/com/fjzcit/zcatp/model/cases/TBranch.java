package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 分支，非单纯的Git分支，而是执业务上的分支
 * （保留设计）
 * 同一个系统可能会根据不同的用户场景划分为不同的分支并部署在不同的服务器上
 */

@Data
@ToString
@Entity
@Table(name = "t_branch")
@org.hibernate.annotations.Table(appliesTo = "t_branch", comment = "分支")
public class TBranch {

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

    // 所属系统
    @Column(name = "system_id",
            nullable = false,
            columnDefinition = "int comment '所属系统'")
    private Integer systemId;

    // URL
    @Column(name = "url",
            nullable = false,
            columnDefinition = "varchar(128) comment '系统域名，最后不用带“/”'"
    )
    private String url;


    // 状态
    @Column(name = "state",
            nullable = false,
            columnDefinition = "int default 1 comment '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "remarks",
            nullable = true,
            columnDefinition = "varchar(2048) comment '备注'")
    private String remarks;
}
