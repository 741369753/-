package com.cyj.entity;

import lombok.Data;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/9/17
 */
@Data
public class GoodsAdd {
    private Goods goods;
    private List<GoodsSku> goodsSku;
}
