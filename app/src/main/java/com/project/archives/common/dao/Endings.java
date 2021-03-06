package com.project.archives.common.dao;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.io.Serializable;
import java.util.Arrays;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "ENDINGS".
 */
@Entity
public class Endings implements Serializable {

    @Id
    private byte[] ID;
    private byte[] UserID;
    private String Name;
    private String Init;
    private String Position;
    private Integer Rank;
    private String IdCard;
    private String Number;
    private String EndingTime;
    private String KeyWord;
    private byte[] Problem;
    private String EndingContent;
    private String SurveyContent;
    private Integer TrueDegree;
    private String Result;
    private byte[] ResultSituation;
    private String Note;
    private byte[] AnnexIDStr;
    private Integer isDelete;
    private String AddDate;
    private String AddUser;
    private String UpdateDate;
    private String UpdateUser;
    private Integer objectSource;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated(hash = 1200741649)
    public Endings() {
    }

    public Endings(byte[] ID) {
        this.ID = ID;
    }

    @Generated(hash = 1829529868)
    public Endings(byte[] ID, byte[] UserID, String Name, String Init, String Position, Integer Rank, String IdCard, String Number, String EndingTime, String KeyWord, byte[] Problem, String EndingContent, String SurveyContent, Integer TrueDegree, String Result, byte[] ResultSituation, String Note, byte[] AnnexIDStr, Integer isDelete, String AddDate, String AddUser, String UpdateDate, String UpdateUser, Integer objectSource) {
        this.ID = ID;
        this.UserID = UserID;
        this.Name = Name;
        this.Init = Init;
        this.Position = Position;
        this.Rank = Rank;
        this.IdCard = IdCard;
        this.Number = Number;
        this.EndingTime = EndingTime;
        this.KeyWord = KeyWord;
        this.Problem = Problem;
        this.EndingContent = EndingContent;
        this.SurveyContent = SurveyContent;
        this.TrueDegree = TrueDegree;
        this.Result = Result;
        this.ResultSituation = ResultSituation;
        this.Note = Note;
        this.AnnexIDStr = AnnexIDStr;
        this.isDelete = isDelete;
        this.AddDate = AddDate;
        this.AddUser = AddUser;
        this.UpdateDate = UpdateDate;
        this.UpdateUser = UpdateUser;
        this.objectSource = objectSource;
    }

    public byte[] getID() {
        return ID;
    }

    public void setID(byte[] ID) {
        this.ID = ID;
    }

    public byte[] getUserID() {
        return UserID;
    }

    public void setUserID(byte[] UserID) {
        this.UserID = UserID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getInit() {
        return Init;
    }

    public void setInit(String Init) {
        this.Init = Init;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer Rank) {
        this.Rank = Rank;
    }

    public String getIdCard() {
        return IdCard;
    }

    public void setIdCard(String IdCard) {
        this.IdCard = IdCard;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    public String getEndingTime() {
        return EndingTime;
    }

    public void setEndingTime(String EndingTime) {
        this.EndingTime = EndingTime;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String KeyWord) {
        this.KeyWord = KeyWord;
    }

    public byte[] getProblem() {
        return Problem;
    }

    public void setProblem(byte[] Problem) {
        this.Problem = Problem;
    }

    public String getEndingContent() {
        return EndingContent;
    }

    public void setEndingContent(String EndingContent) {
        this.EndingContent = EndingContent;
    }

    public String getSurveyContent() {
        return SurveyContent;
    }

    public void setSurveyContent(String SurveyContent) {
        this.SurveyContent = SurveyContent;
    }

    public Integer getTrueDegree() {
        return TrueDegree;
    }

    public void setTrueDegree(Integer TrueDegree) {
        this.TrueDegree = TrueDegree;
    }

    public String getResult() {
        return Result;
    }

    public void setResult(String Result) {
        this.Result = Result;
    }

    public byte[] getResultSituation() {
        return ResultSituation;
    }

    public void setResultSituation(byte[] ResultSituation) {
        this.ResultSituation = ResultSituation;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String Note) {
        this.Note = Note;
    }

    public byte[] getAnnexIDStr() {
        return AnnexIDStr;
    }

    public void setAnnexIDStr(byte[] AnnexIDStr) {
        this.AnnexIDStr = AnnexIDStr;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getAddDate() {
        return AddDate;
    }

    public void setAddDate(String AddDate) {
        this.AddDate = AddDate;
    }

    public String getAddUser() {
        return AddUser;
    }

    public void setAddUser(String AddUser) {
        this.AddUser = AddUser;
    }

    public String getUpdateDate() {
        return UpdateDate;
    }

    public void setUpdateDate(String UpdateDate) {
        this.UpdateDate = UpdateDate;
    }

    public String getUpdateUser() {
        return UpdateUser;
    }

    public void setUpdateUser(String UpdateUser) {
        this.UpdateUser = UpdateUser;
    }

    public Integer getObjectSource() {
        return objectSource;
    }

    public void setObjectSource(Integer objectSource) {
        this.objectSource = objectSource;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

    @Override
    public String toString() {
        return "Endings{" +
                "ID=" + Arrays.toString(ID) +
                ", UserID=" + Arrays.toString(UserID) +
                ", Name='" + Name + '\'' +
                ", Init='" + Init + '\'' +
                ", Position='" + Position + '\'' +
                ", Rank=" + Rank +
                ", IdCard='" + IdCard + '\'' +
                ", Number='" + Number + '\'' +
                ", EndingTime=" + EndingTime +
                ", KeyWord='" + KeyWord + '\'' +
                ", Problem=" + Arrays.toString(Problem) +
                ", EndingContent='" + EndingContent + '\'' +
                ", SurveyContent='" + SurveyContent + '\'' +
                ", TrueDegree=" + TrueDegree +
                ", Result='" + Result + '\'' +
                ", ResultSituation=" + Arrays.toString(ResultSituation) +
                ", Note='" + Note + '\'' +
                ", AnnexIDStr=" + Arrays.toString(AnnexIDStr) +
                ", isDelete=" + isDelete +
                ", AddDate=" + AddDate +
                ", AddUser='" + AddUser + '\'' +
                ", UpdateDate=" + UpdateDate +
                ", UpdateUser='" + UpdateUser + '\'' +
                ", objectSource=" + objectSource +
                '}';
    }
}
