package com.openplan.server.module.category;

import java.util.List;

import com.openplan.server.http.base.ResponseBodyDTO;

public class CategoryDTO implements ResponseBodyDTO {

    private static final long serialVersionUID = -5452811153886883698L;

    private Integer id;

    private Integer consumerType;

    private String name;

    private String intro;

    private List<CategoryItemDTO> items;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(Integer consumerType) {
        this.consumerType = consumerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public List<CategoryItemDTO> getItems() {
        return items;
    }

    public void setItems(List<CategoryItemDTO> items) {
        this.items = items;
    }

}
