package com.fjzcit.zcatp.controller.iteration;

import com.fjzcit.zcatp.service.iteration.IterationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RequestMapping(value = "/iteration")
@RestController
public class IterationController {

    @Resource
    IterationService iterationSvc;

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @ApiOperation(value = "执行迭代", httpMethod = "POST")
    public Object execute(@RequestBody Map<String, String> param) {
        Integer iterationId = Integer.valueOf(param.get("iterationId"));
        this.iterationSvc.execute(iterationId);
        return iterationId;
    }
}
