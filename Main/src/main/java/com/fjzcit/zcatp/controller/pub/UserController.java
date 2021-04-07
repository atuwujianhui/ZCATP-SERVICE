package com.fjzcit.zcatp.controller.pub;

import com.fjzcit.zcatp.model.pub.User;
import com.fjzcit.zcatp.service.pub.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RequestMapping(value = "/sys/user")
@RestController
public class UserController {

    @Resource
    UserService userService;

    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public Object findAll() {
        return this.userService.findAll();
    }

    @RequestMapping(value = "/findById", method = RequestMethod.POST)
    public Object findById(@RequestBody Map<String, String> param) {
        Long id = new Long(Objects.toString(param.get("id"), "0"));
        return this.userService.findById(id);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object save(@RequestBody User user) {
        return this.userService.save(user);
    }

    /**
     * 新增用户
     * @param user
     * @return
     */
    @RequestMapping(value = "/saveByMyBatis", method = RequestMethod.POST)
    public Object saveByMyBatis(@RequestBody User user) {
        return this.userService.saveByMyBatis(user);
    }

    /**
     * 删除用户
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") int id) {
        this.userService.deleteById(id);
    }
}
