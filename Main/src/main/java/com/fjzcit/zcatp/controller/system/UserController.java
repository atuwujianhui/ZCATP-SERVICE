package com.fjzcit.zcatp.controller.system;

import com.fjzcit.zcatp.model.system.User;
import com.fjzcit.zcatp.service.system.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RequestMapping(value = "/system/user")
@RestController
public class UserController {

    @Resource
    UserService userService;

    // @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    @GetMapping("/findAll")
    public Object findAll() {
        return this.userService.findAll();
    }

    // @RequestMapping(value = "/findById", method = RequestMethod.POST)
    @PostMapping("/findById")
    public Object findById(@RequestBody Map<String, String> param) {
        Long id = new Long(Objects.toString(param.get("id"), "0"));
        return this.userService.findById(id);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    // @RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save")
    public Object save(@RequestBody User user) {
        return this.userService.save(user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    // @RequestMapping(value = "/saveByMyBatis", method = RequestMethod.POST)
    @PostMapping("/saveByMyBatis")
    public Object saveByMyBatis(@RequestBody User user) {
        return this.userService.saveByMyBatis(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") int id) {
        this.userService.deleteById(id);
    }
}
