package com.outofwork.java.rateLimiter;

/**
 * @author outofwork
 * created on 15/02/21
 */
public class ReqCounter {
    private Long second = 0L;
    private Long count = 0L;

    public ReqCounter(Long second, Long count) {
        this.second = second;
        this.count = count;
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
