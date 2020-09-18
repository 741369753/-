package com.cyj.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 商品文案
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GoodsCopy {

    @JsonProperty("copyId")
    private Integer copyId;

    private String copyTitle;

    private String copyLink;

    private Integer orderNo;

    private Integer typeId;

    private String goodsId;

    private String customerId;

    private String copyContent;

}