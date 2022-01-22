package com.fjzcit.zcatp.controller.jmeter;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/jmeter")
public class JMeterController {

    // @RequestMapping(value = "/execute", method = RequestMethod.POST)
    @PostMapping(value = "/execute")
    public Object execute(@RequestParam String instId) {
        return "Hello 实例-" + instId + "!";
    }
}
