package com.project.archives.common.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;

import java.io.Serializable;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "Gifts".
 */
@Entity(nameInDb = "Gifts")
public class Gifts implements Serializable {

    @Id
    @Property(nameInDb = "ID")
    private byte[] id;

    @Property(nameInDb = "GiftHandID")
    private byte[] giftHandID;

    @Property(nameInDb = "GiftHandTime")
    private String giftHandTime;

    @Property(nameInDb = "GiftHandAmount")
    private String giftHandAmount;

    @Property(nameInDb = "GiftName")
    private String giftName;

    @Property(nameInDb = "GiftNum")
    private Integer giftNum;

    @Property(nameInDb = "GiftNote")
    private String giftNote;

    @Property(nameInDb = "IsDelete")
    private Integer isDelete;

    @Property(nameInDb = "AddTime")
    private String addTime;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Gifts() {
    }

    public Gifts(byte[] id) {
        this.id = id;
    }

    @Generated
    public Gifts(byte[] id, byte[] giftHandID, String giftHandTime, String giftHandAmount, String giftName, Integer giftNum, String giftNote, Integer isDelete, String addTime) {
        this.id = id;
        this.giftHandID = giftHandID;
        this.giftHandTime = giftHandTime;
        this.giftHandAmount = giftHandAmount;
        this.giftName = giftName;
        this.giftNum = giftNum;
        this.giftNote = giftNote;
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

    public String getGiftHandTime() {
        return giftHandTime;
    }

    public void setGiftHandTime(String giftHandTime) {
        this.giftHandTime = giftHandTime;
    }

    public String getGiftHandAmount() {
        return giftHandAmount;
    }

    public void setGiftHandAmount(String giftHandAmount) {
        this.giftHandAmount = giftHandAmount;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public Integer getGiftNum() {
        return giftNum;
    }

    public void setGiftNum(Integer giftNum) {
        this.giftNum = giftNum;
    }

    public String getGiftNote() {
        return giftNote;
    }

    public void setGiftNote(String giftNote) {
        this.giftNote = giftNote;
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
