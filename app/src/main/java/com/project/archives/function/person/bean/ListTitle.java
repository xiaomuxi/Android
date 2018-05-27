package com.project.archives.function.person.bean;

import java.io.Serializable;

/**
 * Created by inrokei on 2018/5/27.
 */

public class ListTitle implements Serializable{
    private String name;
    private String title;
    private String caseinvesCount;
    private String verificationsCount;
    private String dutyReportCount;
    private String lettersCount;
    private String endingsCount;
    private String zancunsCount;
    private String giftsCount;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCaseinvesCount() {
        return caseinvesCount;
    }

    public void setCaseinvesCount(String caseinvesCount) {
        this.caseinvesCount = caseinvesCount;
    }

    public String getVerificationsCount() {
        return verificationsCount;
    }

    public void setVerificationsCount(String verificationsCount) {
        this.verificationsCount = verificationsCount;
    }

    public String getDutyReportCount() {
        return dutyReportCount;
    }

    public void setDutyReportCount(String dutyReportCount) {
        this.dutyReportCount = dutyReportCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLettersCount() {
        return lettersCount;
    }

    public void setLettersCount(String lettersCount) {
        this.lettersCount = lettersCount;
    }

    public String getEndingsCount() {
        return endingsCount;
    }

    public void setEndingsCount(String endingsCount) {
        this.endingsCount = endingsCount;
    }

    public String getZancunsCount() {
        return zancunsCount;
    }

    public void setZancunsCount(String zancunsCount) {
        this.zancunsCount = zancunsCount;
    }

    public String getGiftsCount() {
        return giftsCount;
    }

    public void setGiftsCount(String giftsCount) {
        this.giftsCount = giftsCount;
    }

    @Override
    public String toString() {
        return "ListTitle{" +
                "name='" + name + '\'' +
                ", title='" + title + '\'' +
                ", caseinvesCount='" + caseinvesCount + '\'' +
                ", verificationsCount='" + verificationsCount + '\'' +
                ", dutyReportCount='" + dutyReportCount + '\'' +
                ", lettersCount='" + lettersCount + '\'' +
                ", endingsCount='" + endingsCount + '\'' +
                ", zancunsCount='" + zancunsCount + '\'' +
                ", giftsCount='" + giftsCount + '\'' +
                '}';
    }
}
