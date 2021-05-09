package com.fjzcit.zcatp.service.cases;

import com.alibaba.fastjson.JSONObject;
import com.fjzcit.zcatp.common.constant.InterfaceTypeEnum;
import com.fjzcit.zcatp.common.constant.http.ContentTypeEnum;
import com.fjzcit.zcatp.common.constant.http.RequestMethodEnum;
import com.fjzcit.zcatp.model.cases.TExecuteBatch;
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
        // 创建执行批次
        TExecuteBatch batch = new TExecuteBatch();
        // TODO: 测试使用，待完善
        batch.setId(1);
        // 执行用例
        resultList = executeCase(batch.getId(), caseList);

        /*
        Optional<List<TInterfaceCase>> opt = Optional.ofNullable(caseList);
        resultList = caseList.stream().map(obj -> executeCase(obj)).collect(Collectors.toList());
        Optional.ofNullable(caseList).ifPresent(cases ->
            cases.stream().map(obj -> executeCase(obj)).collect(Collectors.toList())
        );
        resultList = Optional.ofNullable(caseList).orElse(new ArrayList<>())
                .stream().map(iCase -> executeCase(iCase)).collect(Collectors.toList());
        */

        // 保存用例执行结果
        executeResultSvc.saveAll(resultList);
        // 生成测试报告
        generateReport(iterationId);
    }

    /**
     * 执行用例
     * @param batchId：执行批次
     * @param caseList：执行用例列表
     */
    public List<TExecuteResult> executeCase(Integer batchId, List<TInterfaceCase> caseList) {
        List<TExecuteResult> resultList = new ArrayList<>();

        for (TInterfaceCase iCase: caseList) {
            // 用例执行结果
            TExecuteResult result = new TExecuteResult();
            result.setBatchId(batchId); // 当前执行批次
            result.setSystemId(iCase.getSystem().getId());
            result.setIterationId(iCase.getIteration().getId());    // 迭代/增量ID
            result.setCaseId(iCase.getId());    // 用例ID
            result.setBizType(1);   // 业务类型，待确认
            // 定义结果类型，暂时假定都返回JSONObject类型
            JSONObject responseResult = new JSONObject();
            // 拼装接口地址
            String url = iCase.getSystem().getUrl() + iCase.getUri();
            // 判断接口类型
            switch (InterfaceTypeEnum.getEnum(iCase.getInterfaceType())) {
                // 如果为“HTTP”
                case HTTP:
                    // 判断请求方法
                    switch (RequestMethodEnum.getEnum(iCase.getMethod())) {
                        // 如果为“GET”
                        case GET:
                            responseResult = HttpClientUtils.httpGet(url);
                            break;
                        case POST:
                            // 判断“content-type”
                            switch (ContentTypeEnum.getEnum(iCase.getContentType())) {
                                case FORM:
                                    break;
                                case JSON:
                                    // 构建JSON参数对象
                                    JSONObject params = JSONObject.parseObject(iCase.getParams());
                                    responseResult = HttpClientUtils.httpPost(url, params);
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
            // 预期响应结果类型
            result.setExpectedResponseType(iCase.getExpectedResponseType());
            // 预期响应结果
            result.setExpectedResponseContent(iCase.getExpectedResponseContent());
            // 实际响应结果
            result.setActualResponseContent(responseResult == null ? "" : responseResult.toString());
            // 判断用例执行结果
            result.setExecuteResult(assertExecuteResult(result));
            resultList.add(result);
        }
        return resultList;
    }

    /**
     * 判断用例执行结果：0、不通过、1、通过；
     * @param result
     */
    public Integer assertExecuteResult(TExecuteResult result) {
        // TODO: 测试使用，用例执行通过
        return 1;
    }


    /**
     * 生成测试报告
     */
    public void generateReport(Integer iterationId) {

    }
}
