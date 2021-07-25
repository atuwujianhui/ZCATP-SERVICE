package com.fjzcit.zcatp.service.iteration;

import com.alibaba.fastjson.JSONObject;
import com.fjzcit.zcatp.common.constant.InterfaceTypeEnum;
import com.fjzcit.zcatp.common.constant.ResponseTypeEnum;
import com.fjzcit.zcatp.common.constant.http.ContentTypeEnum;
import com.fjzcit.zcatp.common.constant.http.RequestMethodEnum;
import com.fjzcit.zcatp.model.zcatp.ExecuteBatch;
import com.fjzcit.zcatp.model.zcatp.ExecuteResult;
import com.fjzcit.zcatp.model.zcatp.InterfaceCase;
import com.fjzcit.zcatp.model.zcatp.TestData;
import com.fjzcit.zcatp.repository.iteration.IIterationRepository;
import com.fjzcit.zcatp.service.test.TestDataService;
import com.fjzcit.zcatp.service.test.ExecuteResultService;
import com.fjzcit.zcatp.service.test.InterfaceCaseService;
import com.fjzcit.zcatp.util.HttpClientUtils;
import org.hibernate.annotations.Source;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class IterationService {

    @Source
    IIterationRepository iterationRepository;

    @Resource
    InterfaceCaseService interfaceCaseSvc;

    @Resource
    ExecuteResultService executeResultSvc;

    @Resource
    TestDataService dataSvc;

    /**
     * 执行迭代/增量所属的所有案例
     * @param iterationId
     */
    public void execute(Integer iterationId) {
        List<ExecuteResult> resultList = new ArrayList();
        // 查询所有待查询的用例
        List<InterfaceCase> caseList = this.interfaceCaseSvc.findByIterationId(iterationId);
        // 创建执行批次
        ExecuteBatch batch = new ExecuteBatch();
        // TODO: 测试使用，待完善
        batch.setId(1);
        // 执行用例
        resultList = executeCase(batch.getId(), caseList);

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
    public List<ExecuteResult> executeCase(Integer batchId, List<InterfaceCase> caseList) {
        List<ExecuteResult> resultList = new ArrayList<>();

        for (InterfaceCase iCase: caseList) {
            // 用例执行结果
            ExecuteResult result = new ExecuteResult();
            result.setBatchId(batchId); // 当前执行批次
            result.setSystemId(iCase.getSystem().getId());
            result.setIterationId(iCase.getIteration().getId());    // 迭代/增量ID
            result.setCaseId(iCase.getId());    // 用例ID
            result.setBizType(1);   // 业务类型，待确认
            // 定义结果类型，暂时假定都返回JSONObject类型
            JSONObject responseResult = new JSONObject();
            String responseText = null;
            // 拼装接口地址
            String url = iCase.getSystem().getUrl() + iCase.getUri();
            // 获取接口的参数数据（TODO: 仅供测试，待优化，不宜在循环中读取参数）
            System.out.println(iCase.getId());
            List<TestData> dataList = this.dataSvc.findByCaseId(iCase.getId());
            // 如果没有数据，则退出
            if (dataList == null || dataList.isEmpty()) {
                // 为了保证循环正常运行，需要列表需要添加一个空元素
                // dataList = new ArrayList<>();
                // dataList.add(new TestData());
                break;
            }
            // 遍历参数数据，执行接口测试
            for (TestData data: dataList) {
                // 判断接口类型
                switch (InterfaceTypeEnum.getEnum(iCase.getInterfaceType())) {
                    // 如果为“HTTP”
                    case HTTP:
                        // 判断请求方法
                        switch (RequestMethodEnum.getEnum(iCase.getMethod())) {
                            // 如果为“GET”
                            case GET:
                                // TODO: 拼接参数，参数均为Json格式，需要转化为键值对并与URL拼接
                                url += conventJsonStringToKeyValue(data.getParameter());
                                responseText = HttpClientUtils.httpGet(url);
                                break;
                            case POST:

                                // 构建JSON参数对象
//                                JSONObject params = JSONObject.parseObject(iCase.getParameter());
                                JSONObject params = JSONObject.parseObject(data.getParameter());
                                // 判断“content-type”
                                switch (ContentTypeEnum.getEnum(iCase.getContentType())) {
                                    case FORM:
                                        responseText = HttpClientUtils.httpPost(url, params, ContentTypeEnum.FORM);
                                        break;
                                    case JSON:
                                        responseText = HttpClientUtils.httpPost(url, params, ContentTypeEnum.JSON);
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
                result.setActualResponseContent(responseText);
                // result.setActualResponseContent(responseResult == null ? "" : responseResult.toString());
                // 判断用例执行结果
                result.setExecuteResult(assertExecuteResult(result));
                resultList.add(result);
            }

        }
        return resultList;
    }

    /**
     * TODO: 参数转化键值对
     * @param strParam：json格式字符串
     * @return
     */
    public String conventJsonStringToKeyValue(String strParam) {
        JSONObject json = JSONObject.parseObject(strParam);
        if (json == null) {
            return "";
        }

        Iterator iter = json.entrySet().iterator();
        StringBuffer sb = new StringBuffer("?");
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            sb.append(entry.getKey().toString())
                    .append("=")
                    .append(entry.getValue().toString())
                    .append("&");
        }
        // 删除最后一个字符：?或者&
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * 判断用例执行结果：0、不通过、1、通过；
     * @param result
     */
    public Integer assertExecuteResult(ExecuteResult result) {
        if (result.getExpectedResponseType() == ResponseTypeEnum.STRING.getCode()) {
            if (! result.getExpectedResponseContent().equals(result.getActualResponseContent())) {
                return 0;
            }
        } else if (result.getExpectedResponseType() == ResponseTypeEnum.JSON.getCode()) {
            JSONObject expectedResponse = JSONObject.parseObject(result.getExpectedResponseContent());
            JSONObject actualResponse = JSONObject.parseObject(result.getActualResponseContent());
            // if (! expectedResponse.toString().equals(actualResponse.toString())) {
            if (! expectedResponse.equals(actualResponse)) {
                return 0;
            }
        }
        return 1;
    }


    /**
     * 生成测试报告
     */
    public void generateReport(Integer iterationId) {

    }
}
