package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@ToString
@Entity
@Table(name = "ZCATP_TEST_DATA_FILE")
@org.hibernate.annotations.Table(appliesTo = "zcatp_test_data_file", comment = "用例驱动数据文件")
public class TestDataFile {

    // 主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    // 名称
    @Column(name = "NAME",
            nullable = false,
            columnDefinition = "VARCHAR(128) COMMENT '名称'")
    private String name;
}
