package com.cyj.entity;

public class GoodsType {
    private String typeId;

    private String typeName;

    private String parentId;

    private String typeTag;

    private Integer orderNo;

    private String alisaCode;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId == null ? null : typeId.trim();
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getTypeTag() {
        return typeTag;
    }

    public void setTypeTag(String typeTag) {
        this.typeTag = typeTag == null ? null : typeTag.trim();
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }

    public String getAlisaCode() {
        return alisaCode;
    }

    public void setAlisaCode(String alisaCode) {
        this.alisaCode = alisaCode == null ? null : alisaCode.trim();
    }
}