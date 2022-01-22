package com.fjzcit.zcatp.controller.test;

import com.fjzcit.zcatp.service.test.InterfaceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping(value = "/interfaceCase")
@RestController
@Api(value = "Swagger2接口测试")
public class InterfaceCaseController {

    @Resource
    InterfaceService interfaceSvc;

    @RequestMapping(value = "/execute/{iterationId}", method = RequestMethod.GET)
    @ApiOperation(value = "执行接口测试用例！", httpMethod = "GET")
    public Object executeCase(@PathVariable("iterationId") String iterationId) {
        this.interfaceSvc.executeCase(Integer.valueOf(iterationId));
        return iterationId;
    }

}
