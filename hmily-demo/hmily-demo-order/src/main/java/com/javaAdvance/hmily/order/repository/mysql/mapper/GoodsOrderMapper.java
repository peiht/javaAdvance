package com.javaAdvance.hmily.order.repository.mysql.mapper;

import com.javaAdvance.hmily.order.repository.mysql.domain.GoodsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author hitopei
 * @since 2020-12-09
 */
public interface GoodsOrderMapper extends BaseMapper<GoodsOrder> {

    @Update("update goods_order set order_status = #{orderStatus} where " +
            "order_id = #{orderId}")
    int update(@Param("orderId") Long orderId, @Param("orderStatus") Integer orderStatus);

}
