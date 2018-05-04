package com.greendao;

import org.greenrobot.greendao.generator.DaoGenerator;
import org.greenrobot.greendao.generator.Entity;
import org.greenrobot.greendao.generator.Schema;

public class MyGenerator {

    public static void main(String[] args) {
        Schema schema = new Schema(1, "com.project.archives.common.dao");
        schema.enableKeepSectionsByDefault();

        addTables(schema);

        try {
            new DaoGenerator().generateAll(schema,"./app/src/main/java");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addTables(final Schema schema) {
        addUserEntities(schema);

//        addCaseInvesEntities(schema);
//        addVerificationsEntities(schema);
//        addLettersEntities(schema);
//        addEndingsEntities(schema);
//        addZancunsEntities(schema);
//        addMultiDictionariesEntities(schema);
    }

    private static void addUserEntities(Schema schema) {
        Entity entity = schema.addEntity("Users");
        entity.addByteArrayProperty("ID").primaryKey();
        entity.addIntProperty("IsDelete");
        entity.addStringProperty("RealName");
        entity.addStringProperty("IDCard");
        entity.addIntProperty("Sex");
        entity.addIntProperty("Age");
        entity.addStringProperty("National");
        entity.addStringProperty("NativePlace");
        entity.addStringProperty("HomePlace");
        entity.addStringProperty("Health");
        entity.addStringProperty("ZPosition");
        entity.addStringProperty("Specialty");
        entity.addStringProperty("QEducation");
        entity.addStringProperty("QSchool");
        entity.addStringProperty("Education");
        entity.addStringProperty("School");
        entity.addStringProperty("Resume");
        entity.addStringProperty("BonusPenalty");
        entity.addStringProperty("CheckResult");
        entity.addStringProperty("Position");
        entity.addStringProperty("Birth");
        entity.addStringProperty("DataType");
        entity.addStringProperty("AddDate");
        entity.addStringProperty("AddUser");
        entity.addStringProperty("UpdateDate");
        entity.addStringProperty("UpdateUser");
        entity.addStringProperty("ContactPhone");
        entity.addStringProperty("HomeAddress");
        entity.addStringProperty("BirthYear");
        entity.addStringProperty("PictureUrl");
        entity.addStringProperty("PictureName");
        entity.addStringProperty("PartyTimeStr");
        entity.addStringProperty("WorkTimeStr");
        entity.addStringProperty("Init");
        entity.addIntProperty("Rank");
        entity.addStringProperty("IsCadre");
        entity.addIntProperty("CbInit");
        entity.addStringProperty("IsDone");
        entity.addStringProperty("IsDoneW");
        entity.addStringProperty("IsDoneJ");
        entity.addStringProperty("IsRen");
        entity.addStringProperty("IsZheng");
        entity.addStringProperty("GerenID");

    }

    private static Entity addMultiDictionariesEntities(Schema schema) {
        Entity entity = schema.addEntity("MultiDictionaries");
        entity.addByteArrayProperty("ID").primaryKey();
        entity.addStringProperty("OptionsName");
        entity.addIntProperty("Type");
        entity.addIntProperty("Sort");
        entity.addIntProperty("IsDelete");
        entity.addDateProperty("AddDate");
        entity.addStringProperty("AddUser");
        entity.addDateProperty("UpdateDate");
        entity.addStringProperty("UpdateUser");

        return entity;
    }

    private static Entity addZancunsEntities(Schema schema) {
        Entity entity = schema.addEntity("Zancuns");
        entity.addByteArrayProperty("UserID");
        entity.addStringProperty("Name");
        entity.addStringProperty("Init");
        entity.addStringProperty("Position");
        entity.addIntProperty("Rank");
        entity.addStringProperty("IdCard");
        entity.addStringProperty("Number");
        entity.addDateProperty("EndingTime");
        entity.addStringProperty("KeyWord");
        entity.addByteArrayProperty("Problem");
        entity.addStringProperty("EndingContent");
        entity.addStringProperty("SurveyContent");
        entity.addIntProperty("TrueDegree");
        entity.addStringProperty("Result");
        entity.addByteArrayProperty("ResultSituation");
        entity.addStringProperty("Note");
//        endings.addIntProperty("Organ");
        entity.addByteArrayProperty("AnnexIDStr");
        entity.addIntProperty("isDelete");
        entity.addDateProperty("AddDate");
        entity.addStringProperty("AddUser");
        entity.addDateProperty("UpdateDate");
        entity.addStringProperty("UpdateUser");
        entity.addIntProperty("objectSource");

        return entity;
    }

    private static Entity addEndingsEntities(final Schema schema) {
        Entity endings = schema.addEntity("Endings");
        endings.addByteArrayProperty("ID").primaryKey();
        endings.addByteArrayProperty("UserID");
        endings.addStringProperty("Name");
        endings.addStringProperty("Init");
        endings.addStringProperty("Position");
        endings.addIntProperty("Rank");
        endings.addStringProperty("IdCard");
        endings.addStringProperty("Number");
        endings.addDateProperty("EndingTime");
        endings.addStringProperty("KeyWord");
        endings.addByteArrayProperty("Problem");
        endings.addStringProperty("EndingContent");
        endings.addStringProperty("SurveyContent");
        endings.addIntProperty("TrueDegree");
        endings.addStringProperty("Result");
        endings.addByteArrayProperty("ResultSituation");
        endings.addStringProperty("Note");
//        endings.addIntProperty("Organ");
        endings.addByteArrayProperty("AnnexIDStr");
        endings.addIntProperty("isDelete");
        endings.addDateProperty("AddDate");
        endings.addStringProperty("AddUser");
        endings.addDateProperty("UpdateDate");
        endings.addStringProperty("UpdateUser");
        endings.addIntProperty("objectSource");

        return endings;
    }

    private static Entity addLettersEntities(final Schema schema) {
        Entity letters = schema.addEntity("Letters");
        letters.addByteArrayProperty("ID").primaryKey();
        letters.addByteArrayProperty("UserID");
        letters.addStringProperty("Name");
        letters.addStringProperty("Init");
        letters.addStringProperty("Position");
        letters.addIntProperty("Rank");
        letters.addStringProperty("IdCard");
        letters.addStringProperty("Number");
        letters.addDateProperty("LetterTime");
        letters.addStringProperty("KeyWord");
        letters.addByteArrayProperty("Problem");
        letters.addStringProperty("LetterContent");
        letters.addStringProperty("SurveyContent");
        letters.addIntProperty("TrueDegree");
        letters.addStringProperty("Result");
        letters.addByteArrayProperty("ResultSituation");
        letters.addStringProperty("Note");
        letters.addIntProperty("Organ");
        letters.addByteArrayProperty("AnnexIDStr");
        letters.addIntProperty("isDelete");
        letters.addDateProperty("AddDate");
        letters.addStringProperty("AddUser");
        letters.addDateProperty("UpdateDate");
        letters.addStringProperty("UpdateUser");
        letters.addIntProperty("objectSource");

        return letters;
    }

    private static Entity addVerificationsEntities(final Schema schema) {
        Entity verifications = schema.addEntity("Verifications");
        verifications.addByteArrayProperty("ID").primaryKey();
        verifications.addByteArrayProperty("UserID");
        verifications.addStringProperty("Name");
        verifications.addStringProperty("Init");
        verifications.addStringProperty("Position");
        verifications.addIntProperty("Rank");
        verifications.addIntProperty("Coding");
        verifications.addIntProperty("IsObject");
        verifications.addIntProperty("IsOfficer");
        verifications.addByteArrayProperty("Discipline");
        verifications.addIntProperty("Organ");
        verifications.addDateProperty("VerificTime");
        verifications.addDateProperty("TakingTime");
        verifications.addStringProperty("Clues");
        verifications.addStringProperty("VerificResult");
        verifications.addIntProperty("TakingResult");
        verifications.addIntProperty("ResultSituation");
        verifications.addStringProperty("Note");
        verifications.addByteArrayProperty("AnnexIDStr");
        verifications.addIntProperty("isDelete");
        verifications.addDateProperty("AddDate");
        verifications.addStringProperty("AddUser");
        verifications.addDateProperty("UpdateDate");
        verifications.addStringProperty("UpdateUser");
        verifications.addIntProperty("objectSource");
        verifications.addDateProperty("ProcessTime");
        verifications.addStringProperty("Trail");

        return verifications;
    }

    private static Entity addCaseInvesEntities(final Schema schema) {
        Entity caseInves = schema.addEntity("CaseInves");
        caseInves.addByteArrayProperty("ID").primaryKey();
        caseInves.addByteArrayProperty("UserID");
        caseInves.addStringProperty("Name");
        caseInves.addStringProperty("Init");
        caseInves.addStringProperty("Position");
        caseInves.addIntProperty("Rank");
        caseInves.addIntProperty("Coding");
        caseInves.addIntProperty("IsPcongress");
        caseInves.addIntProperty("IsMember");
        caseInves.addIntProperty("IsObject");
        caseInves.addIntProperty("IsOfficer");
        caseInves.addIntProperty("IsPartyMember");
        caseInves.addStringProperty("Facts");
        caseInves.addByteArrayProperty("Discipline");
        caseInves.addIntProperty("Organ");
        caseInves.addDateProperty("PutTime");
        caseInves.addDateProperty("OutTime");
        caseInves.addStringProperty("SurveyContent");
        caseInves.addIntProperty("DisTypeD");
        caseInves.addStringProperty("Note");
        caseInves.addByteArrayProperty("AnnexIDStr");
        caseInves.addIntProperty("isDelete");
        caseInves.addDateProperty("AddDate");
        caseInves.addStringProperty("AddUser");
        caseInves.addDateProperty("UpdateDate");
        caseInves.addStringProperty("UpdateUser");
        caseInves.addStringProperty("PoliticsStatus");
        caseInves.addIntProperty("Organization");
        caseInves.addIntProperty("Xiansou");
        caseInves.addDateProperty("ShouTime");
        caseInves.addStringProperty("Trail");
        caseInves.addStringProperty("Description");
        caseInves.addDateProperty("ChuheTime");
        caseInves.addDateProperty("LiaojieTime");

        return caseInves;
    }
}
