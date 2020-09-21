package com.cyj.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Order {
    private String orderId;

    private String goodId;

    private Integer buyNum;

    private Date orderTime;

    private String channelId;

    private Integer state;

    private Integer payType;

    private String skuId;

    private String userId;

    private String buyerName;

    private String buyerPhone;

    private String province;

    private String city;

    private String area;

    private String address;

    private String buyerReamrk;

    private String courierId;

    private String orderRemark;

    private String senderType;

    private Integer orderType;

    private String adminRemark;

    private String operator;

    private Date checkTime;

    private String orderIp;

    private Date updateTime;

    private String alipayState;

    private Goods goods;

    private GoodsSku goodsSku;

    private Member member;
}