package service;

import dto.Exposer;
import dto.SeckillExecution;
import entity.Seckill;
import exception.RepeatKillException;
import exception.SeckillCloseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


/**
 * Created by ranxiong on 2016/6/21.
 */

/**
 * 配置spring和junit整合，junit启动时加载springIOC容器
 * spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉 junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"})
public class SeckillServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckillService seckillService;

    @Test
    public void testGetSeckillList() throws Exception {
        List<Seckill> list = seckillService.getSeckillList();
        logger.info("list={}", list);//占位符打印
    }

    @Test
    public void testGetById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillService.getById(id);
        logger.info("seckill={}", seckill);
    }

    @Test
    public void testExportSeckillUrl() throws Exception {
        long id = 1000;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("expose={}", exposer);
        //expose=Exposer{exposed=true, MD5='b21100e9a51ce2a77cd3211325ec41aa', seckillId=1000, now=0, start=0, end=0}
    }

    @Test
    public void testExecuteSeckill() throws Exception {
        long id = 1000;
        long phone = 15111890420L;
        String md5 = "b21100e9a51ce2a77cd3211325ec41aa";
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
            logger.info("result={}", seckillExecution);
            //result=SeckillExecution{seckillId=1000, state=1, stateInfo='秒杀成功', successKilled=SuccessKilled{seckill_id=0,
            // user_phone=0, state=0, createTime=null, seckill=Seckill{seckill_id=0, name='1000元秒杀iPhone6', number=0,
            // createTime=Wed Jun 22 21:37:11 CST 2016, startTime=Fri Jan 01 00:00:00 CST 2016, endTime=Thu Dec 01 00:00:00 CST 2016}}}
        } catch (SeckillCloseException e) {
            logger.error(e.getMessage());
        } catch (RepeatKillException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * 综合上面两个测试,继承测试代码完整逻辑，注意可重复执行
     */
    @Test
    public void testSeckillLogic() {
        long id = 1002;
        Exposer exposer = seckillService.exportSeckillUrl(id);
        logger.info("expose={}", exposer);
        if (exposer.isExposed()) {
            long phone = 15111890420L;
            String md5 = exposer.getMD5();
            try {
                SeckillExecution seckillExecution = seckillService.executeSeckill(id, phone, md5);
                logger.info("result={}", seckillExecution);
            } catch (SeckillCloseException e) {
                logger.error(e.getMessage());
            } catch (RepeatKillException e) {
                logger.error(e.getMessage());
            }
        } else {
            //秒杀未开启
            logger.warn("expose={}", exposer);
        }
    }

    @Test
    public void excuteSeckillPhoneProcedure() {
        long seckillId = 1001;
        long phone = 15111890421L;
        Exposer exposer = seckillService.exportSeckillUrl(seckillId);
        if (exposer.isExposed()) {
            String md5 = exposer.getMD5();
            SeckillExecution seckillExecution = seckillService.executeSeckillProcedure(seckillId, phone, md5);
            logger.info(seckillExecution.getStateInfo());
        }
    }
}