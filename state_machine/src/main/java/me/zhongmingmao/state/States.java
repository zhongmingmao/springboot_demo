package me.zhongmingmao.state;

/**
 * 订单状态
 * UNPAID --(PAY)--> WAITING_FOR_RECEIVE
 * WAITING_FOR_RECEIVE --(RECEIVE)--> DONE
 */
public enum States {
    UNPAID,                 // 待支付
    WAITING_FOR_RECEIVE,    // 待收货
    DONE                    // 完成
}
