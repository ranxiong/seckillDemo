package dao;

import entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉 junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {
    //注入dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        long id = 1000L;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception {
        List<Seckill> seckills = seckillDao.queryAll(0,100);
        //java没有保存形参的记录
        // queryAll(int offset,int limit);->queryAll(arg0,arg1);
        //解决方法，增加@Param注解绑定参数名称
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date date = new Date();
        System.out.println(seckillDao.reduceNumber(1000L, date));
    }
}