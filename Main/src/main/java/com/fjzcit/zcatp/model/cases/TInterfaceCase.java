package com.fjzcit.zcatp.model.cases;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

/**
 * 接口测试用例
 */

@Data
@ToString
@Entity
@Table(name = "t_interface_case")
@org.hibernate.annotations.Table(appliesTo = "t_interface_case", comment = "接口测试用例")
public class TInterfaceCase {

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

    // 所属迭代/增量
    @Column(name = "iteration_id",
            nullable = false,
            columnDefinition = "int comment '所属迭代/增量'")
    private Integer iterationId;

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

//    // 是否执行
//    @Column(name = "execute_flag",
//            nullable = true,
//            columnDefinition = "int default 1 comment '是否执行'")
//    private Integer executeFlag;

    // 前置条件，即依赖的用例ID
    @Column(name = "precondition",
            nullable = true,
            columnDefinition = "int comment '前置条件，即依赖的用例ID'")
    private Integer precondition;

    // 接口类型
    @Column(name = "interface_type",
            nullable = false,
            columnDefinition = "int default 0 comment '接口类型，0：http；1：socket；2：webservice；' "
    )
    private Integer interfaceType;

    // 接口URL
    @Column(name = "url",
            nullable = false,
            columnDefinition = "varchar(256) comment '接口URL'"
    )
    private String url;

    // 请求方法
    @Column(name = "method",
            nullable = false,
            columnDefinition = "int default 0 comment '接口请求方法，0：get；1：post；2：put；3：delete；'"
    )
    private Integer method;

    // 是否为Ajax请求，如果是，需要设置请求头参数”requestedWith“为”XMLHttpRequest“
    @Column(name = "is_ajax",
            nullable = false,
            columnDefinition = "tinyint default 0 comment '是否为Ajax请求，0：否；1：是；'"
    )
    private Integer isAjax;

    // 媒体类型，即MediaType，也叫MIME类型，使用POST
    /**
     * 0: application/x-www-form-urlencoded
     * 1: application/json
     * 2: multipart/form-data
     * 3: text/xml
     */
    @Column(name = "content_type",
            nullable = false,
            columnDefinition = "tinyint default 0 comment '媒体类型'"
    )
    private Integer contentType;

    // 请求参数，根据“content-type”，选择不同的类型
    @Column(name = "params",
            nullable = true,
            columnDefinition = "varchar(128) comment '请求参数'"
    )
    private String params;

    // 预期结果类型
    @Column(name = "expected_response_type",
            nullable = true,
            columnDefinition = "tinyint comment '预期结果类型'"
    )
    private String expectedResponseType;

    // 预期结果
    @Column(name = "expected_response_content",
            nullable = true,
            columnDefinition = "varchar(2048) comment '预期返回结果'"
    )
    private String expectedResponseContent;

    // 是否更新cookie，一般用于登录
    @Column(name = "update_cookie",
            nullable = false,
            columnDefinition = "tinyint default 1 comment '是否使用当前操作返回的Cookie更新本次系统Cookie，0：不更新'")
    private Integer updateCookie;

    // 状态
    @Column(name = "state",
            nullable = false,
            columnDefinition = "tinyint default 1 comment '状态，0：无效；1：有效。'")
    private Integer state;

    // 备注
    @Column(name = "remarks",
            nullable = true,
            columnDefinition = "varchar(2048) comment '备注'")
    private String remarks;

}
