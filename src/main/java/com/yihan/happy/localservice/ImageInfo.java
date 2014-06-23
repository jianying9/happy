package com.yihan.happy.localservice;

import java.util.HashMap;
import java.util.Map;

/**
 *
 *
 * @author aladdin
 */
public final class ImageInfo {

    private long id;
    //
    private String title;
    //
    private String content;
    //
    private String tag;
    //
    private long voteUp;
    //
    private long voteDown;
    //
    private long share;
    //
    private long comment;
    //
    private long createTime;
    //
    private long height;
    //
    private long width;
    //
    private String picurl;
    //
    private String sPicurl;
    //
    private long sHeight;
    //
    private long sWidth;
    //
    private String mPicurl;
    //
    private long mHeight;
    //
    private long mWidth;
    //
    private String fromUrl;
    //
    private String fromName;
    //
    private String linkUrl;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getVoteUp() {
        return voteUp;
    }

    public void setVoteUp(long voteUp) {
        this.voteUp = voteUp;
    }

    public long getVoteDown() {
        return voteDown;
    }

    public void setVoteDown(long voteDown) {
        this.voteDown = voteDown;
    }

    public long getShare() {
        return share;
    }

    public void setShare(long share) {
        this.share = share;
    }

    public long getComment() {
        return comment;
    }

    public void setComment(long comment) {
        this.comment = comment;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getsPicurl() {
        return sPicurl;
    }

    public void setsPicurl(String sPicurl) {
        this.sPicurl = sPicurl;
    }

    public long getsHeight() {
        return sHeight;
    }

    public void setsHeight(long sHeight) {
        this.sHeight = sHeight;
    }

    public long getsWidth() {
        return sWidth;
    }

    public void setsWidth(long sWidth) {
        this.sWidth = sWidth;
    }

    public String getmPicurl() {
        return mPicurl;
    }

    public void setmPicurl(String mPicurl) {
        this.mPicurl = mPicurl;
    }

    public long getmHeight() {
        return mHeight;
    }

    public void setmHeight(long mHeight) {
        this.mHeight = mHeight;
    }

    public long getmWidth() {
        return mWidth;
    }

    public void setmWidth(long mWidth) {
        this.mWidth = mWidth;
    }

    public String getFromUrl() {
        return fromUrl;
    }

    public void setFromUrl(String fromUrl) {
        this.fromUrl = fromUrl;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }
    
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<String, String>(32, 1);
        map.put("id", Long.toString(this.id));
        map.put("title", this.title);
        map.put("content", this.content);
        map.put("tag", this.tag);
        map.put("voteUp", Long.toString(this.voteUp));
        map.put("voteDown", Long.toString(this.voteDown));
        map.put("share", Long.toString(this.share));
        map.put("comment", Long.toString(this.comment));
        map.put("createTime", Long.toString(this.createTime));
        map.put("height", Long.toString(this.height));
        map.put("width", Long.toString(this.width));
        map.put("picurl", this.picurl);
        map.put("sPicurl", this.sPicurl);
        map.put("sHeight", Long.toString(this.sHeight));
        map.put("sWidth", Long.toString(this.sWidth));
        map.put("mPicurl", this.mPicurl);
        map.put("mHeight", Long.toString(this.mHeight));
        map.put("mWidth", Long.toString(this.mWidth));
        map.put("fromUrl", this.fromUrl);
        map.put("fromName", this.fromName);
        map.put("linkUrl", this.linkUrl);
        return map;
    }
    
    @Override
    public String toString() {
        return this.toMap().toString();
    }
}
