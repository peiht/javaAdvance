package com.javaAdvance.sharding.service.impl;

import com.javaAdvance.sharding.base.ResultBean;
import com.javaAdvance.sharding.repository.mysql.domain.User;
import com.javaAdvance.sharding.repository.mysql.mapper.UserMapper;
import com.javaAdvance.sharding.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopei
 * @since 2020-12-02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public ResultBean getUser(Integer id) {
        return ResultBean.success(this.getById(id));
    }

    @Override
    public ResultBean addUser(User user) {
        user.setcTime(new Date());
        this.save(user);
        return ResultBean.success();
    }
}
