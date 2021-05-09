package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

/**
 * 迭代/增量
 */

@Data
@ToString
@Entity
@Table(name = "t_iteration")
@org.hibernate.annotations.Table(appliesTo = "t_iteration", comment = "迭代")
public class TIteration {

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

    // 所属系统
    @Column(name = "system_id",
            nullable = false,
            columnDefinition = "int comment '所属系统'")
    private Integer systemId;

    // 创建时间
    @CreatedDate
    private Long createTime;

    // 备注
    @Column(name = "remarks",
            nullable = true,
            columnDefinition = "varchar(2048) comment '备注'")
    private String remarks;
}
