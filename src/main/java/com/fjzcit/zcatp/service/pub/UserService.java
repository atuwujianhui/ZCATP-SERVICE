package com.fjzcit.zcatp.service.pub;

import com.fjzcit.zcatp.model.pub.User;
import com.fjzcit.zcatp.repository.pub.UserRepository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {
    @Resource
    UserRepository userRepository;

    public Object save(User user) {
        return this.userRepository.save(user);
    }

    public void deleteById(int id) {
        this.userRepository.deleteById(id);
    }
}
