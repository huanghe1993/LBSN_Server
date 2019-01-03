package com.huanghe.lbsn.Entity;

import java.io.Serializable;

public class Poi implements Serializable {
    private Integer poiId;

    private String address;

    private String cityName;

    private Float longitude;

    private Float latitude;

    private String category;

    private String poiDescribe;

    private String photo;

    private String tinyPhoto;

    private static final long serialVersionUID = 1L;

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName == null ? null : cityName.trim();
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category == null ? null : category.trim();
    }

    public String getPoiDescribe() {
        return poiDescribe;
    }

    public void setPoiDescribe(String poiDescribe) {
        this.poiDescribe = poiDescribe == null ? null : poiDescribe.trim();
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo == null ? null : photo.trim();
    }

    public String getTinyPhoto() {
        return tinyPhoto;
    }

    public void setTinyPhoto(String tinyPhoto) {
        this.tinyPhoto = tinyPhoto == null ? null : tinyPhoto.trim();
    }
}