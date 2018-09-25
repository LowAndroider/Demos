package com.exam.category.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    /**
     * 在系统中的缩略图id
     * MediaStore.Video.Thumbnails._ID
     */
    private int imgId;

    /**
     * 缩略图path  MediaStore.Video.Thumbnails.DATA
     */
    private String imgData;

    /**
     * 视频id
     */
    private int id;

    /**
     * 视频path  MediaStore.Video.Thumbnails.DATA
     */
    private String data;

    /**
     * 视频显示名称
     */
    private String displayName;

    /**
     * 视频大小 kb
     */
    private long size;

    /**
     * 时长 ms
     */
    private long duration;

    public Movie() {

    }

    protected Movie(Parcel in) {
        imgId = in.readInt();
        imgData = in.readString();
        id = in.readInt();
        data = in.readString();
        displayName = in.readString();
        size = in.readLong();
        duration = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(imgId);
        dest.writeString(imgData);
        dest.writeInt(id);
        dest.writeString(data);
        dest.writeString(displayName);
        dest.writeLong(size);
        dest.writeLong(duration);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public int getImgId() {
        return imgId;
    }

    public Movie setImgId(int imgId) {
        this.imgId = imgId;
        return this;
    }

    public String getImgData() {
        return imgData;
    }

    public Movie setImgData(String imgData) {
        this.imgData = imgData;
        return this;
    }

    public int getId() {
        return id;
    }

    public Movie setId(int id) {
        this.id = id;
        return this;
    }

    public String getDisplayName() {
        return displayName;
    }

    public Movie setDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    public long getSize() {
        return size;
    }

    public Movie setSize(long size) {
        this.size = size;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public Movie setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    public String getData() {
        return data;
    }

    public Movie setData(String data) {
        this.data = data;
        return this;
    }

}
