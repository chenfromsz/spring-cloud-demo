package com.demo.common;

import java.io.Serializable;

public class PageQo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer page = 0;
    private Integer size = 10;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
