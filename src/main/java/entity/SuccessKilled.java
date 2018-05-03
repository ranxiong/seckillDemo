package entity;

import java.util.Date;

/**
 * 秒杀记录
 * Created by Administrator on 2016-06-08.
 */
public class SuccessKilled {

    private long seckill_id;
    private long user_phone;
    private short state;
    private Date createTime;
    private Seckill seckill;

    //多对一
    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public long getSeckill_id() {
        return seckill_id;
    }

    public void setSeckill_id(long seckill_id) {
        this.seckill_id = seckill_id;
    }

    public long getUser_phone() {
        return user_phone;
    }

    public void setUser_phone(long user_phone) {
        this.user_phone = user_phone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckill_id=" + seckill_id +
                ", user_phone=" + user_phone +
                ", state=" + state +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }
}
