package com.javaAdvance.hmily.order.web;

import com.javaAdvance.hmily.order.base.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ty
 *
 * order
 */
@RestController
@RequestMapping("order")
public class OrderController {

    @RequestMapping("pay")
    public ResultBean pay(@RequestParam("userId") Integer userId,
                          @RequestParam("stockId") Integer stockId,
                          @RequestParam("amount") Integer amount,
                          @RequestParam("count") Integer count){
        return ResultBean.success();
    }
}
