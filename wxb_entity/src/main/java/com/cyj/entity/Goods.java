package com.cyj.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Goods {
    private String goodId;

    private String goodName;

    private String customerId;

    private String goodPic1;

    private String goodPic2;

    private String goodPic3;

    private String promoteDesc;

    private String copyIds;

    private String copyDesc;

    private String forwardLink;

    private Integer orderNo;

    private String typeId;

    private String tags;

    private Integer state;

    private Date createTime;

    private Integer toped;

    private Integer recomed;

    private Date topedTime;

    private Date recomedTime;

    private String spcId;

    private String zonId;

    private Integer sellNum;

    private String website;

    private String kfqq;

    private Customer customer;

    private GoodsType goodsType;

}