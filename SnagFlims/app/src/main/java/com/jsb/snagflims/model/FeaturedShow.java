package com.jsb.snagflims.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FeaturedShow {

    @SerializedName("tray_items")
    @Expose
    private List<TrayItem> trayItems = null;
    @SerializedName("tray_name")
    @Expose
    private String trayName;
    @SerializedName("view_type")
    @Expose
    private String viewType;

    public List<TrayItem> getTrayItems() {
        return trayItems;
    }

    public void setTrayItems(List<TrayItem> trayItems) {
        this.trayItems = trayItems;
    }

    public String getTrayName() {
        return trayName;
    }

    public void setTrayName(String trayName) {
        this.trayName = trayName;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

}
