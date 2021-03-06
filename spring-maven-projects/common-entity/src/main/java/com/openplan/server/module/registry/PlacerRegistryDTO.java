package com.openplan.server.module.registry;

import com.openplan.server.http.base.ResponseBodyDTO;

public class PlacerRegistryDTO implements ResponseBodyDTO {
    private static final long serialVersionUID = -237781381963384517L;

    private Integer id;

    private String unid;

    private Integer placerId;

    private Integer corporationId;

    private Boolean enable;

    private Long updateAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUnid() {
        return unid;
    }

    public void setUnid(String unid) {
        this.unid = unid == null ? null : unid.trim();
    }

    public Integer getPlacerId() {
        return placerId;
    }

    public void setPlacerId(Integer placerId) {
        this.placerId = placerId;
    }

    public Integer getCorporationId() {
        return corporationId;
    }

    public void setCorporationId(Integer corporationId) {
        this.corporationId = corporationId;
    }

    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    public Long getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Long updateAt) {
        this.updateAt = updateAt;
    }

}