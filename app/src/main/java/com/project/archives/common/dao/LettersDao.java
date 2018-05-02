package com.project.archives.common.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "Letters".
*/
public class LettersDao extends AbstractDao<Letters, byte[]> {

    public static final String TABLENAME = "Letters";

    /**
     * Properties of entity Letters.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, byte[].class, "ID", true, "ID");
        public final static Property UserID = new Property(1, byte[].class, "UserID", false, "UserID");
        public final static Property Name = new Property(2, String.class, "Name", false, "Name");
        public final static Property Init = new Property(3, String.class, "Init", false, "Init");
        public final static Property Position = new Property(4, String.class, "Position", false, "Position");
        public final static Property Rank = new Property(5, Integer.class, "Rank", false, "Rank");
        public final static Property IdCard = new Property(6, String.class, "IdCard", false, "IdCard");
        public final static Property Number = new Property(7, String.class, "Number", false, "Number");
        public final static Property LetterTime = new Property(8, String.class, "LetterTime", false, "LetterTime");
        public final static Property KeyWord = new Property(9, String.class, "KeyWord", false, "KeyWord");
        public final static Property Problem = new Property(10, byte[].class, "Problem", false, "Problem");
        public final static Property LetterContent = new Property(11, String.class, "LetterContent", false, "LetterContent");
        public final static Property SurveyContent = new Property(12, String.class, "SurveyContent", false, "SurveyContent");
        public final static Property TrueDegree = new Property(13, Integer.class, "TrueDegree", false, "TrueDegree");
        public final static Property Result = new Property(14, String.class, "Result", false, "Result");
        public final static Property ResultSituation = new Property(15, byte[].class, "ResultSituation", false, "ResultSituation");
        public final static Property Note = new Property(16, String.class, "Note", false, "Note");
        public final static Property Organ = new Property(17, Integer.class, "Organ", false, "Organ");
        public final static Property AnnexIDStr = new Property(18, byte[].class, "AnnexIDStr", false, "AnnexIDStr");
        public final static Property IsDelete = new Property(19, Integer.class, "isDelete", false, "isDelete");
        public final static Property AddDate = new Property(20, String.class, "AddDate", false, "AddDate");
        public final static Property AddUser = new Property(21, String.class, "AddUser", false, "AddUser");
        public final static Property UpdateDate = new Property(22, String.class, "UpdateDate", false, "UpdateDate");
        public final static Property UpdateUser = new Property(23, String.class, "UpdateUser", false, "UpdateUser");
        public final static Property ObjectSource = new Property(24, Integer.class, "objectSource", false, "objectSource");
    }


    public LettersDao(DaoConfig config) {
        super(config);
    }
    
    public LettersDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Letters\" (" + //
                "\"ID\" BLOB PRIMARY KEY ," + // 0: ID
                "\"UserID\" BLOB," + // 1: UserID
                "\"Name\" TEXT," + // 2: Name
                "\"Init\" TEXT," + // 3: Init
                "\"Position\" TEXT," + // 4: Position
                "\"Rank\" INTEGER," + // 5: Rank
                "\"IdCard\" TEXT," + // 6: IdCard
                "\"Number\" TEXT," + // 7: Number
                "\"LetterTime\" TEXT," + // 8: LetterTime
                "\"KeyWord\" TEXT," + // 9: KeyWord
                "\"Problem\" BLOB," + // 10: Problem
                "\"LetterContent\" TEXT," + // 11: LetterContent
                "\"SurveyContent\" TEXT," + // 12: SurveyContent
                "\"TrueDegree\" INTEGER," + // 13: TrueDegree
                "\"Result\" TEXT," + // 14: Result
                "\"ResultSituation\" BLOB," + // 15: ResultSituation
                "\"Note\" TEXT," + // 16: Note
                "\"Organ\" INTEGER," + // 17: Organ
                "\"AnnexIDStr\" BLOB," + // 18: AnnexIDStr
                "\"isDelete\" INTEGER," + // 19: isDelete
                "\"AddDate\" TEXT," + // 20: AddDate
                "\"AddUser\" TEXT," + // 21: AddUser
                "\"UpdateDate\" TEXT," + // 22: UpdateDate
                "\"UpdateUser\" TEXT," + // 23: UpdateUser
                "\"objectSource\" INTEGER);"); // 24: objectSource
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Letters\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Letters entity) {
        stmt.clearBindings();
 
        byte[] ID = entity.getID();
        if (ID != null) {
            stmt.bindBlob(1, ID);
        }
 
        byte[] UserID = entity.getUserID();
        if (UserID != null) {
            stmt.bindBlob(2, UserID);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(3, Name);
        }
 
        String Init = entity.getInit();
        if (Init != null) {
            stmt.bindString(4, Init);
        }
 
        String Position = entity.getPosition();
        if (Position != null) {
            stmt.bindString(5, Position);
        }
 
        Integer Rank = entity.getRank();
        if (Rank != null) {
            stmt.bindLong(6, Rank);
        }
 
        String IdCard = entity.getIdCard();
        if (IdCard != null) {
            stmt.bindString(7, IdCard);
        }
 
        String Number = entity.getNumber();
        if (Number != null) {
            stmt.bindString(8, Number);
        }

        String LetterTime = entity.getLetterTime();
        if (LetterTime != null) {
            stmt.bindString(9, LetterTime);
        }
 
        String KeyWord = entity.getKeyWord();
        if (KeyWord != null) {
            stmt.bindString(10, KeyWord);
        }
 
        byte[] Problem = entity.getProblem();
        if (Problem != null) {
            stmt.bindBlob(11, Problem);
        }
 
        String LetterContent = entity.getLetterContent();
        if (LetterContent != null) {
            stmt.bindString(12, LetterContent);
        }
 
        String SurveyContent = entity.getSurveyContent();
        if (SurveyContent != null) {
            stmt.bindString(13, SurveyContent);
        }
 
        Integer TrueDegree = entity.getTrueDegree();
        if (TrueDegree != null) {
            stmt.bindLong(14, TrueDegree);
        }
 
        String Result = entity.getResult();
        if (Result != null) {
            stmt.bindString(15, Result);
        }
 
        byte[] ResultSituation = entity.getResultSituation();
        if (ResultSituation != null) {
            stmt.bindBlob(16, ResultSituation);
        }
 
        String Note = entity.getNote();
        if (Note != null) {
            stmt.bindString(17, Note);
        }
 
        Integer Organ = entity.getOrgan();
        if (Organ != null) {
            stmt.bindLong(18, Organ);
        }
 
        byte[] AnnexIDStr = entity.getAnnexIDStr();
        if (AnnexIDStr != null) {
            stmt.bindBlob(19, AnnexIDStr);
        }
 
        Integer isDelete = entity.getIsDelete();
        if (isDelete != null) {
            stmt.bindLong(20, isDelete);
        }

        String AddDate = entity.getAddDate();
        if (AddDate != null) {
            stmt.bindString(21, AddDate);
        }
 
        String AddUser = entity.getAddUser();
        if (AddUser != null) {
            stmt.bindString(22, AddUser);
        }

        String UpdateDate = entity.getUpdateDate();
        if (UpdateDate != null) {
            stmt.bindString(23, UpdateDate);
        }
 
        String UpdateUser = entity.getUpdateUser();
        if (UpdateUser != null) {
            stmt.bindString(24, UpdateUser);
        }
 
        Integer objectSource = entity.getObjectSource();
        if (objectSource != null) {
            stmt.bindLong(25, objectSource);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Letters entity) {
        stmt.clearBindings();
 
        byte[] ID = entity.getID();
        if (ID != null) {
            stmt.bindBlob(1, ID);
        }
 
        byte[] UserID = entity.getUserID();
        if (UserID != null) {
            stmt.bindBlob(2, UserID);
        }
 
        String Name = entity.getName();
        if (Name != null) {
            stmt.bindString(3, Name);
        }
 
        String Init = entity.getInit();
        if (Init != null) {
            stmt.bindString(4, Init);
        }
 
        String Position = entity.getPosition();
        if (Position != null) {
            stmt.bindString(5, Position);
        }
 
        Integer Rank = entity.getRank();
        if (Rank != null) {
            stmt.bindLong(6, Rank);
        }
 
        String IdCard = entity.getIdCard();
        if (IdCard != null) {
            stmt.bindString(7, IdCard);
        }
 
        String Number = entity.getNumber();
        if (Number != null) {
            stmt.bindString(8, Number);
        }

        String LetterTime = entity.getLetterTime();
        if (LetterTime != null) {
            stmt.bindString(9, LetterTime);
        }
 
        String KeyWord = entity.getKeyWord();
        if (KeyWord != null) {
            stmt.bindString(10, KeyWord);
        }
 
        byte[] Problem = entity.getProblem();
        if (Problem != null) {
            stmt.bindBlob(11, Problem);
        }
 
        String LetterContent = entity.getLetterContent();
        if (LetterContent != null) {
            stmt.bindString(12, LetterContent);
        }
 
        String SurveyContent = entity.getSurveyContent();
        if (SurveyContent != null) {
            stmt.bindString(13, SurveyContent);
        }
 
        Integer TrueDegree = entity.getTrueDegree();
        if (TrueDegree != null) {
            stmt.bindLong(14, TrueDegree);
        }
 
        String Result = entity.getResult();
        if (Result != null) {
            stmt.bindString(15, Result);
        }
 
        byte[] ResultSituation = entity.getResultSituation();
        if (ResultSituation != null) {
            stmt.bindBlob(16, ResultSituation);
        }
 
        String Note = entity.getNote();
        if (Note != null) {
            stmt.bindString(17, Note);
        }
 
        Integer Organ = entity.getOrgan();
        if (Organ != null) {
            stmt.bindLong(18, Organ);
        }
 
        byte[] AnnexIDStr = entity.getAnnexIDStr();
        if (AnnexIDStr != null) {
            stmt.bindBlob(19, AnnexIDStr);
        }
 
        Integer isDelete = entity.getIsDelete();
        if (isDelete != null) {
            stmt.bindLong(20, isDelete);
        }

        String AddDate = entity.getAddDate();
        if (AddDate != null) {
            stmt.bindString(21, AddDate);
        }
 
        String AddUser = entity.getAddUser();
        if (AddUser != null) {
            stmt.bindString(22, AddUser);
        }

        String UpdateDate = entity.getUpdateDate();
        if (UpdateDate != null) {
            stmt.bindString(23, UpdateDate);
        }
 
        String UpdateUser = entity.getUpdateUser();
        if (UpdateUser != null) {
            stmt.bindString(24, UpdateUser);
        }
 
        Integer objectSource = entity.getObjectSource();
        if (objectSource != null) {
            stmt.bindLong(25, objectSource);
        }
    }

    @Override
    public byte[] readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0);
    }    

    @Override
    public Letters readEntity(Cursor cursor, int offset) {
        Letters entity = new Letters( //
            cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getBlob(offset + 1), // UserID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Init
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Position
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // Rank
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // IdCard
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // Number
            cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8), // LetterTime
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // KeyWord
            cursor.isNull(offset + 10) ? null : cursor.getBlob(offset + 10), // Problem
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // LetterContent
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // SurveyContent
            cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13), // TrueDegree
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // Result
            cursor.isNull(offset + 15) ? null : cursor.getBlob(offset + 15), // ResultSituation
            cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16), // Note
            cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17), // Organ
            cursor.isNull(offset + 18) ? null : cursor.getBlob(offset + 18), // AnnexIDStr
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // isDelete
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // AddDate
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // AddUser
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // UpdateDate
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // UpdateUser
            cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24) // objectSource
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Letters entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getBlob(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setInit(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPosition(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRank(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setIdCard(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setNumber(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setLetterTime(cursor.isNull(offset + 8) ? null : cursor.getString(offset + 8));
        entity.setKeyWord(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setProblem(cursor.isNull(offset + 10) ? null : cursor.getBlob(offset + 10));
        entity.setLetterContent(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setSurveyContent(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setTrueDegree(cursor.isNull(offset + 13) ? null : cursor.getInt(offset + 13));
        entity.setResult(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setResultSituation(cursor.isNull(offset + 15) ? null : cursor.getBlob(offset + 15));
        entity.setNote(cursor.isNull(offset + 16) ? null : cursor.getString(offset + 16));
        entity.setOrgan(cursor.isNull(offset + 17) ? null : cursor.getInt(offset + 17));
        entity.setAnnexIDStr(cursor.isNull(offset + 18) ? null : cursor.getBlob(offset + 18));
        entity.setIsDelete(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setAddDate(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setAddUser(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setUpdateDate(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setUpdateUser(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setObjectSource(cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24));
     }
    
    @Override
    protected final byte[] updateKeyAfterInsert(Letters entity, long rowId) {
        return entity.getID();
    }
    
    @Override
    public byte[] getKey(Letters entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Letters entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
