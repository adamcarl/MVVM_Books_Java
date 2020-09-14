package com.example.mvvmbooksjava.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Volume {

    @SerializedName("kind")
    @Expose
    private String kind;

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("eTag")
    @Expose
    private String eTag;

    @SerializedName("selfLink")
    @Expose
    private String selfLink;

    @SerializedName("volumeInfo")
    @Expose
    VolumeInfo volumeInfo;

    public String getKind() {
        return kind;
    }

    public String getId() {
        return id;
    }

    public String geteTag() {
        return eTag;
    }

    public String getSelfLink() {
        return selfLink;
    }

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }
}
