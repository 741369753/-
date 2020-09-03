package com.cyj.entity;

import lombok.Data;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/27
 */
@Data
public class TreeNodeData {
    private String title;
    private String id;
    private boolean checked;
    private List<TreeNodeData> children;
}
