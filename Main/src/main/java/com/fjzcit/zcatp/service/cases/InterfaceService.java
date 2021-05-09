package com.fjzcit.zcatp.service.cases;

import com.alibaba.fastjson.JSONObject;
import com.fjzcit.zcatp.common.constant.InterfaceTypeEnum;
import com.fjzcit.zcatp.common.constant.http.ContentTypeEnum;
import com.fjzcit.zcatp.common.constant.http.RequestMethodEnum;
import com.fjzcit.zcatp.model.cases.TExecuteResult;
import com.fjzcit.zcatp.model.cases.TInterfaceCase;
import com.fjzcit.zcatp.model.cases.TIteration;
import com.fjzcit.zcatp.util.HttpClientUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InterfaceService {

    @Resource
    InterfaceCaseService interfaceCaseSvc;

    @Resource
    ExecuteResultService executeResultSvc;

    /**
     * 执行迭代/增量所属的所有案例
     * @param iterationId
     */
    public void executeCase(Integer iterationId) {
        List<TExecuteResult> resultList = new ArrayList();
        // 查询所有待查询的用例
        List<TInterfaceCase> caseList = this.interfaceCaseSvc.findByIterationId(iterationId);
        // 执行用例
//        resultList = executeCase(caseList);
        Optional<List<TInterfaceCase>> opt = Optional.ofNullable(caseList);

        // 执行用例列表
        // resultList = caseList.stream().map(obj -> executeCase(obj)).collect(Collectors.toList());
//        Optional.ofNullable(caseList).ifPresent(cases ->
//            cases.stream().map(obj -> executeCase(obj)).collect(Collectors.toList())
//        );
        resultList = Optional.ofNullable(caseList).orElse(new ArrayList<>())
                .stream().map(iCase -> executeCase(iCase)).collect(Collectors.toList());
        // 保存用例执行结果
        executeResultSvc.saveAll(resultList);
        // 生成测试报告
        generateReport(iterationId);
    }

    /**
     * 执行用例
     * @param iCase
     */
    public TExecuteResult executeCase(TInterfaceCase iCase) {
        TExecuteResult result = new TExecuteResult();
        // 定义结果类型，暂时假定都返回JSONObject类型
        JSONObject responseResult = new JSONObject();
        // 判断接口类型
        switch (InterfaceTypeEnum.getEnum(iCase.getInterfaceType())) {
            // 如果为“HTTP”
            case HTTP:
                // 判断请求方法
                switch (RequestMethodEnum.getEnum(iCase.getMethod())) {
                    // 如果为“GET”
                    case GET:
                        responseResult = HttpClientUtils.httpGet(iCase.getUrl());
                        break;
                    case POST:
                        // 判断“content-type”
                        switch (ContentTypeEnum.getEnum(iCase.getContentType())) {
                            case FORM:
                                break;
                            case JSON:
                                // 构建JSON参数对象
                                JSONObject params = JSONObject.parseObject(iCase.getParams());
                                responseResult = HttpClientUtils.httpPost(iCase.getUrl(), params);
                                break;
                            case MULTIPART:
                                break;
                            case TEXT:
                                break;
                            default:
                                break;
                        }
                        break;
                    case PUT:
                        break;
                    case DELETE:
                        break;
                    default:
                        break;
                }
                break;
            case SOCKET:
                break;
            case WEBSERVICE:
                break;
            default:
                break;
        }

        result.setActualResponseContent(responseResult.toString());
        return result;
    }

    /**
     * 执行用例列表
     * @param caseList
     */
//    public List<TExecuteResult> executeCases(List<TInterfaceCase> caseList) {
//        List<TExecuteResult> resultList = new ArrayList();
//        for(TInterfaceCase interfaceCase: caseList) {
//            // 定义结果类型，暂时假定都返回JSONObject类型
//            JSONObject responseResult = new JSONObject();
//            // 判断接口类型
//            switch (InterfaceTypeEnum.getEnum(interfaceCase.getInterfaceType())) {
//                // 如果为“HTTP”
//                case HTTP:
//                    // 判断请求方法
//                    switch (RequestMethodEnum.getEnum(interfaceCase.getMethod())) {
//                        // 如果为“GET”
//                        case GET:
//                            responseResult = HttpClientUtils.httpGet(interfaceCase.getUrl());
//                            break;
//                        case POST:
//                            // 判断“content-type”
//                            switch (ContentTypeEnum.getEnum(interfaceCase.getContentType())) {
//                                case FORM:
//                                    break;
//                                case JSON:
//                                    // 构建JSON参数对象
//                                    JSONObject params = JSONObject.parseObject(interfaceCase.getParams());
//                                    responseResult = HttpClientUtils.httpPost(interfaceCase.getUrl(), params);
//                                    break;
//                                case MULTIPART:
//                                    break;
//                                case TEXT:
//                                    break;
//                                default:
//                                    break;
//                            }
//                            break;
//                        case PUT:
//                            break;
//                        case DELETE:
//                            break;
//                        default:
//                            break;
//                    }
//                    break;
//                case SOCKET:
//                    break;
//                case WEBSERVICE:
//                    break;
//                default:
//                    break;
//            }
//
//        }
//        return resultList;
//    }

    /**
     * 生成测试报告
     */
    public void generateReport(Integer iterationId) {

    }
}
