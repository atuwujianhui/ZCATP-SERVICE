package com.fjzcit.zcatp.model.test;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 接口测试用例
 */

@Data
@ToString
@Entity
@Table(name = "ZCATP_INTERFACE_CASE")
@org.hibernate.annotations.Table(appliesTo = "zcatp_interface_case", comment = "接口测试用例")
public class InterfaceCase {

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

    // 所属迭代/增量
//    @Column(name = "iteration_id",
//            nullable = true,
//            columnDefinition = "int comment '所属迭代/增量'")
//    private Integer iterationId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "ITERATION_ID")
    private Iteration iteration;

    // 所属系统
//    @Column(name = "system_id",
//            nullable = false,
//            columnDefinition = "int comment '所属系统'")
//    private Integer systemId;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "SYSTEM_ID")
    private System system;

    // 所属模块
    @Column(name = "MODULE_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '所属模块'")
    private Integer moduleId;

    // 所属功能
    @Column(name = "FUNCTION_ID",
            nullable = true,
            columnDefinition = "INT COMMENT '所属功能'")
    private Integer functionId;

//    // 是否执行
//    @Column(name = "execute_flag",
//            nullable = true,
//            columnDefinition = "int default 1 comment '是否执行'")
//    private Integer executeFlag;

    // 前置条件，即依赖的用例ID
    @Column(name = "PRECONDITION",
            nullable = true,
            columnDefinition = "INT COMMENT '前置条件，即依赖的用例ID'")
    private Integer precondition;

    // 接口类型
    @Column(name = "INTERFACE_TYPE",
            nullable = false,
            columnDefinition = "INT DEFAULT 0 COMMENT '接口类型，0：http；1：socket；2：webservice；' "
    )
    private Integer interfaceType;

    // 接口URI
    @Column(name = "URI",
            nullable = false,
            columnDefinition = "VARCHAR(256) COMMENT '接口URI'"
    )
    private String uri;

    // 请求方法
    @Column(name = "METHOD",
            nullable = false,
            columnDefinition = "INT DEFAULT 0 COMMENT '接口请求方法，0：get；1：post；2：put；3：delete；'"
    )
    private Integer method;

    // 是否为Ajax请求，如果是，需要设置请求头参数”requestedWith“为”XMLHttpRequest“
    @Column(name = "IS_AJAX",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT 0 COMMENT '是否为Ajax请求，0：否；1：是；'"
    )
    private Integer isAjax;

    // 媒体类型，即MediaType，也叫MIME类型，使用POST
    /**
     * get请求头中没有“content-type”这个字段
     * 0: application/x-www-form-urlencoded
     * 1: application/json
     * 2: multipart/form-data
     * 3: text/xml
     */
    @Column(name = "CONTENT_TYPE",
            nullable = true,
            columnDefinition = "TINYINT DEFAULT 0 COMMENT '媒体类型'"
    )
    private Integer contentType;

    // 请求参数来源，包括：系统内保存数据，外部文件数据等，待确认
    @Column(name = "PARAMETER_SOURCE",
            nullable = true,
            columnDefinition = "VARCHAR(128) COMMENT '请求参数来源，包括：系统内保存数据，外部文件数据等，待确认'"
    )
    private String parameter_source;

    // 请求参数，目前暂时全部设计为JSON格式保存，前端以表单形式维护，参考Postman
    @Column(name = "PARAMETER",
            nullable = true,
            columnDefinition = "VARCHAR(128) COMMENT '请求参数，目前暂时全部设计为JSON格式保存，前端以表单形式维护，参考Postman'"
    )
    private String parameter;

    // 预期响应结果类型
    @Column(name = "EXPECTED_RESPONSE_TYPE",
            nullable = true,
            columnDefinition = "TINYINT DEFAULT 0 COMMENT '预期响应结果类型，0：文本；1：json'"
    )
    private Integer expectedResponseType;

    // 预期结果
    @Column(name = "EXPECTED_RESPONSE_CONTENT",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '预期返回结果'"
    )
    private String expectedResponseContent;

    // 是否更新cookie，一般用于登录
    @Column(name = "UPDATE_COOKIE",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT 0 COMMENT '是否使用当前操作返回的Cookie更新本次系统Cookie，0：不更新，1：更新'")
    private Integer updateCookie;

    // 状态
    @Column(name = "STATE",
            nullable = false,
            columnDefinition = "TINYINT DEFAULT 1 COMMENT '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "REMARKS",
            nullable = true,
            columnDefinition = "VARCHAR(2048) COMMENT '备注'")
    private String remarks;

}
