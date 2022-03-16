package com.example.judgmentdoc.vo;

import com.example.judgmentdoc.po.Catalog;

import java.util.ArrayList;
import java.util.List;

public class TreeVO {
    private Long id;
    private String label;
    private Long parentId;
    private List<TreeVO> children;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public List<TreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<TreeVO> children) {
        this.children = children;
    }

    public TreeVO() {

    }

    public TreeVO(Catalog catalog) {
        this.id = catalog.getId();
        this.label = catalog.getName();
        this.parentId = catalog.getParentId();
        this.children = new ArrayList<>();
    }
}
