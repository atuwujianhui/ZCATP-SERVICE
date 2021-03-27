package com.fjzcit.zcatp.repository.pub;

import com.fjzcit.zcatp.model.pub.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 参数一 T :当前需要映射的实体
 * 参数二 ID :当前映射的实体中的OID的类型
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}
