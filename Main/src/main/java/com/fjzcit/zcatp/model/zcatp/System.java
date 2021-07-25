package com.fjzcit.zcatp.model.zcatp;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

//TODO：后续再扩展版本/增量/分支管理

/**
 * 系统
 */

@Data
@ToString
@Entity
@Table(name = "ZCATP_SYSTEM")
// TODO: 表名使用大写报错，可能是因为数据库大小写不敏感，生成的数据库对象都是小写导致的
@org.hibernate.annotations.Table(appliesTo = "zcatp_system", comment = "系统")
public class System {
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

    // URL
    @Column(name = "URL",
            nullable = false,
            columnDefinition = "VARCHAR(128) COMMENT '系统域名，最后不用带“/”'"
    )
    private String url;

    // 状态
    @Column(name = "STATE",
            nullable = false,
            columnDefinition = "INT(1) DEFAULT 1 COMMENT '状态，0：无效；1：有效。'")
    private Integer state;

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

    // 备注
    @Column(name = "REMARKS",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '备注'")
    private String remarks;
}
