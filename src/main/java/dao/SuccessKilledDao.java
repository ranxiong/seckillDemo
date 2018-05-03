package dao;

import entity.SuccessKilled;
import org.apache.ibatis.annotations.Param;

/**
 * Created by Administrator on 2016-06-08.
 */
public interface SuccessKilledDao {

    /**
     * 插入购买明细,可过滤重复
     *
     * @param seckillId
     * @param user_phone
     * @return
     */
    int insertSuccesskilled(@Param("seckillId") long seckillId, @Param("user_phone") long user_phone);

    /**
     * 根据ID查询SuccessKilled并携带秒杀产品对象实体
     *
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") long seckillId,@Param("userPhone") long userPhone);
}
