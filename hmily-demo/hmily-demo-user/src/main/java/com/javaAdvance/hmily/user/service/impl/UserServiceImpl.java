package com.javaAdvance.hmily.user.service.impl;

import com.javaAdvance.hmily.user.repository.mysql.domain.User;
import com.javaAdvance.hmily.user.repository.mysql.mapper.UserMapper;
import com.javaAdvance.hmily.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hitopei
 * @since 2020-12-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
