package com.javaAdvance.hmily.user.controller;

import com.javaAdvance.hmily.user.base.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ty
 *
 * user
 */
@RestController
@RequestMapping("user")
public class UserController {

    /**
     *
     * @param userId 用户id
     * @param amount 计算之后需要扣除的金额
     * @return
     */
    @RequestMapping("dealAmount")
    public ResultBean dealAmount(@RequestParam("userId") Integer userId,
                                 @RequestParam("amount") Integer amount){
        return ResultBean.success();
    }
}
