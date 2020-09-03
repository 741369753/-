package com.cyj.entity;

import lombok.Data;

import java.util.List;

/**
 * author:aizhishang
 * time:2020/8/27
 */
@Data
public class CheckedCode {
    private List<String> codes;
    private String roleId;
}
