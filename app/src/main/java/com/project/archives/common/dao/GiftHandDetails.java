package com.project.archives.common.dao;

import org.greenrobot.greendao.annotation.*;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "GiftHandDetails".
 */
@Entity(nameInDb = "GiftHandDetails")
public class GiftHandDetails implements Serializable{

    @Id
    @Property(nameInDb = "ID")
    private byte[] id;

    @Property(nameInDb = "GiftHandID")
    private byte[] giftHandID;

    @Property(nameInDb = "HandTime")
    private String handTime;

    @Property(nameInDb = "HandAmount")
    private Integer handAmount;

    @Property(nameInDb = "HandNote")
    private String handNote;

    @Property(nameInDb = "IsDelete")
    private Integer isDelete;

    @Property(nameInDb = "AddTime")
    private String addTime;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public GiftHandDetails() {
    }

    public GiftHandDetails(byte[] id) {
        this.id = id;
    }

    @Generated
    public GiftHandDetails(byte[] id, byte[] giftHandID, String handTime, Integer handAmount, String handNote, Integer isDelete, String addTime) {
        this.id = id;
        this.giftHandID = giftHandID;
        this.handTime = handTime;
        this.handAmount = handAmount;
        this.handNote = handNote;
        this.isDelete = isDelete;
        this.addTime = addTime;
    }

    public byte[] getId() {
        return id;
    }

    public void setId(byte[] id) {
        this.id = id;
    }

    public byte[] getGiftHandID() {
        return giftHandID;
    }

    public void setGiftHandID(byte[] giftHandID) {
        this.giftHandID = giftHandID;
    }

    public String getHandTime() {
        return handTime;
    }

    public void setHandTime(String handTime) {
        this.handTime = handTime;
    }

    public Integer getHandAmount() {
        return handAmount;
    }

    public void setHandAmount(Integer handAmount) {
        this.handAmount = handAmount;
    }

    public String getHandNote() {
        return handNote;
    }

    public void setHandNote(String handNote) {
        this.handNote = handNote;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
