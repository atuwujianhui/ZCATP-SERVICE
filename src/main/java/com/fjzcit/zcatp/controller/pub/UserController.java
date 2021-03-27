package com.fjzcit.zcatp.controller.pub;

import com.fjzcit.zcatp.model.pub.User;
import com.fjzcit.zcatp.service.pub.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RequestMapping(value = "/sys/user")
@RestController
public class UserController {

    @Resource
    UserService userService;

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
     * 删除用户
     * @param id
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void deleteById(@PathVariable("id") int id) {
        this.userService.deleteById(id);
    }
}
