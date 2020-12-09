package com.javaAdvance.hmily.stock.web;

import com.javaAdvance.hmily.stock.base.ResultBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ty
 */
@RestController
@RequestMapping("stock")
public class StockController {

    @RequestMapping("dealStock")
    public ResultBean dealStock(@RequestParam("stockId") Integer stockId,
                                @RequestParam("count") Integer count){
        return ResultBean.success();
    }
}
