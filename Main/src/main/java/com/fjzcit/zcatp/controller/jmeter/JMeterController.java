package com.fjzcit.zcatp.controller.jmeter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/jmeter")
public class JMeterController {

    @RequestMapping(value = "/execute", method = RequestMethod.POST)
    public Object execute(@RequestParam String instId) {
        return "Hello 实例-" + instId + "!";
    }
}
