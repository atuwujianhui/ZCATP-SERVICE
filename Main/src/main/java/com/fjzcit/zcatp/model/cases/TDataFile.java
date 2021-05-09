package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "t_data_file")
@org.hibernate.annotations.Table(appliesTo = "t_data_file", comment = "用例驱动数据文件")
public class TDataFile {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    // 名称
    @Column(name = "name",
            nullable = false,
            columnDefinition = "varchar(128) comment '名称'")
    private String name;
}
