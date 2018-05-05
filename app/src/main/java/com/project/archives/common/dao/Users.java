package com.project.archives.common.dao;

import org.greenrobot.greendao.annotation.*;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END

/**
 * Entity mapped to table "USERS".
 */
@Entity
public class Users {

    @Id
    private byte[] ID;
    private Integer IsDelete;
    private String RealName;
    private String IDCard;
    private Integer Sex;
    private Integer Age;
    private String National;
    private String NativePlace;
    private String HomePlace;
    private String Health;
    private String ZPosition;
    private String Specialty;
    private String QEducation;
    private String QSchool;
    private String Education;
    private String School;
    private String Resume;
    private String BonusPenalty;
    private String CheckResult;
    private String Position;
    private String Birth;
    private String DataType;
    private String AddDate;
    private String AddUser;
    private String UpdateDate;
    private String UpdateUser;
    private String ContactPhone;
    private String HomeAddress;
    private String BirthYear;
    private String PictureUrl;
    private String PictureName;
    private String PartyTimeStr;
    private String WorkTimeStr;
    private String Init;
    private Integer Rank;
    private String IsCadre;
    private Integer CbInit;
    private String IsDone;
    private String IsDoneW;
    private String IsDoneJ;
    private String IsRen;
    private String IsZheng;
    private String GerenID;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    @Generated
    public Users() {
    }

    public Users(byte[] ID) {
        this.ID = ID;
    }

    @Generated
    public Users(byte[] ID, Integer IsDelete, String RealName, String IDCard, Integer Sex, Integer Age, String National, String NativePlace, String HomePlace, String Health, String ZPosition, String Specialty, String QEducation, String QSchool, String Education, String School, String Resume, String BonusPenalty, String CheckResult, String Position, String Birth, String DataType, String AddDate, String AddUser, String UpdateDate, String UpdateUser, String ContactPhone, String HomeAddress, String BirthYear, String PictureUrl, String PictureName, String PartyTimeStr, String WorkTimeStr, String Init, Integer Rank, String IsCadre, Integer CbInit, String IsDone, String IsDoneW, String IsDoneJ, String IsRen, String IsZheng, String GerenID) {
        this.ID = ID;
        this.IsDelete = IsDelete;
        this.RealName = RealName;
        this.IDCard = IDCard;
        this.Sex = Sex;
        this.Age = Age;
        this.National = National;
        this.NativePlace = NativePlace;
        this.HomePlace = HomePlace;
        this.Health = Health;
        this.ZPosition = ZPosition;
        this.Specialty = Specialty;
        this.QEducation = QEducation;
        this.QSchool = QSchool;
        this.Education = Education;
        this.School = School;
        this.Resume = Resume;
        this.BonusPenalty = BonusPenalty;
        this.CheckResult = CheckResult;
        this.Position = Position;
        this.Birth = Birth;
        this.DataType = DataType;
        this.AddDate = AddDate;
        this.AddUser = AddUser;
        this.UpdateDate = UpdateDate;
        this.UpdateUser = UpdateUser;
        this.ContactPhone = ContactPhone;
        this.HomeAddress = HomeAddress;
        this.BirthYear = BirthYear;
        this.PictureUrl = PictureUrl;
        this.PictureName = PictureName;
        this.PartyTimeStr = PartyTimeStr;
        this.WorkTimeStr = WorkTimeStr;
        this.Init = Init;
        this.Rank = Rank;
        this.IsCadre = IsCadre;
        this.CbInit = CbInit;
        this.IsDone = IsDone;
        this.IsDoneW = IsDoneW;
        this.IsDoneJ = IsDoneJ;
        this.IsRen = IsRen;
        this.IsZheng = IsZheng;
        this.GerenID = GerenID;
    }

    public byte[] getID() {
        return ID;
    }

    public void setID(byte[] ID) {
        this.ID = ID;
    }

    public Integer getIsDelete() {
        return IsDelete;
    }

    public void setIsDelete(Integer IsDelete) {
        this.IsDelete = IsDelete;
    }

    public String getRealName() {
        return RealName;
    }

    public void setRealName(String RealName) {
        this.RealName = RealName;
    }

    public String getIDCard() {
        return IDCard;
    }

    public void setIDCard(String IDCard) {
        this.IDCard = IDCard;
    }

    public Integer getSex() {
        return Sex;
    }

    public void setSex(Integer Sex) {
        this.Sex = Sex;
    }

    public Integer getAge() {
        return Age;
    }

    public void setAge(Integer Age) {
        this.Age = Age;
    }

    public String getNational() {
        return National;
    }

    public void setNational(String National) {
        this.National = National;
    }

    public String getNativePlace() {
        return NativePlace;
    }

    public void setNativePlace(String NativePlace) {
        this.NativePlace = NativePlace;
    }

    public String getHomePlace() {
        return HomePlace;
    }

    public void setHomePlace(String HomePlace) {
        this.HomePlace = HomePlace;
    }

    public String getHealth() {
        return Health;
    }

    public void setHealth(String Health) {
        this.Health = Health;
    }

    public String getZPosition() {
        return ZPosition;
    }

    public void setZPosition(String ZPosition) {
        this.ZPosition = ZPosition;
    }

    public String getSpecialty() {
        return Specialty;
    }

    public void setSpecialty(String Specialty) {
        this.Specialty = Specialty;
    }

    public String getQEducation() {
        return QEducation;
    }

    public void setQEducation(String QEducation) {
        this.QEducation = QEducation;
    }

    public String getQSchool() {
        return QSchool;
    }

    public void setQSchool(String QSchool) {
        this.QSchool = QSchool;
    }

    public String getEducation() {
        return Education;
    }

    public void setEducation(String Education) {
        this.Education = Education;
    }

    public String getSchool() {
        return School;
    }

    public void setSchool(String School) {
        this.School = School;
    }

    public String getResume() {
        return Resume;
    }

    public void setResume(String Resume) {
        this.Resume = Resume;
    }

    public String getBonusPenalty() {
        return BonusPenalty;
    }

    public void setBonusPenalty(String BonusPenalty) {
        this.BonusPenalty = BonusPenalty;
    }

    public String getCheckResult() {
        return CheckResult;
    }

    public void setCheckResult(String CheckResult) {
        this.CheckResult = CheckResult;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public String getBirth() {
        return Birth;
    }

    public void setBirth(String Birth) {
        this.Birth = Birth;
    }

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String DataType) {
        this.DataType = DataType;
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

    public String getContactPhone() {
        return ContactPhone;
    }

    public void setContactPhone(String ContactPhone) {
        this.ContactPhone = ContactPhone;
    }

    public String getHomeAddress() {
        return HomeAddress;
    }

    public void setHomeAddress(String HomeAddress) {
        this.HomeAddress = HomeAddress;
    }

    public String getBirthYear() {
        return BirthYear;
    }

    public void setBirthYear(String BirthYear) {
        this.BirthYear = BirthYear;
    }

    public String getPictureUrl() {
        return PictureUrl;
    }

    public void setPictureUrl(String PictureUrl) {
        this.PictureUrl = PictureUrl;
    }

    public String getPictureName() {
        return PictureName;
    }

    public void setPictureName(String PictureName) {
        this.PictureName = PictureName;
    }

    public String getPartyTimeStr() {
        return PartyTimeStr;
    }

    public void setPartyTimeStr(String PartyTimeStr) {
        this.PartyTimeStr = PartyTimeStr;
    }

    public String getWorkTimeStr() {
        return WorkTimeStr;
    }

    public void setWorkTimeStr(String WorkTimeStr) {
        this.WorkTimeStr = WorkTimeStr;
    }

    public String getInit() {
        return Init;
    }

    public void setInit(String Init) {
        this.Init = Init;
    }

    public Integer getRank() {
        return Rank;
    }

    public void setRank(Integer Rank) {
        this.Rank = Rank;
    }

    public String getIsCadre() {
        return IsCadre;
    }

    public void setIsCadre(String IsCadre) {
        this.IsCadre = IsCadre;
    }

    public Integer getCbInit() {
        return CbInit;
    }

    public void setCbInit(Integer CbInit) {
        this.CbInit = CbInit;
    }

    public String getIsDone() {
        return IsDone;
    }

    public void setIsDone(String IsDone) {
        this.IsDone = IsDone;
    }

    public String getIsDoneW() {
        return IsDoneW;
    }

    public void setIsDoneW(String IsDoneW) {
        this.IsDoneW = IsDoneW;
    }

    public String getIsDoneJ() {
        return IsDoneJ;
    }

    public void setIsDoneJ(String IsDoneJ) {
        this.IsDoneJ = IsDoneJ;
    }

    public String getIsRen() {
        return IsRen;
    }

    public void setIsRen(String IsRen) {
        this.IsRen = IsRen;
    }

    public String getIsZheng() {
        return IsZheng;
    }

    public void setIsZheng(String IsZheng) {
        this.IsZheng = IsZheng;
    }

    public String getGerenID() {
        return GerenID;
    }

    public void setGerenID(String GerenID) {
        this.GerenID = GerenID;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}