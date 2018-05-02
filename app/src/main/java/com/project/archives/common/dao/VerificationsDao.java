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
 * DAO for table "Verifications".
*/
public class VerificationsDao extends AbstractDao<Verifications, byte[]> {

    public static final String TABLENAME = "Verifications";

    /**
     * Properties of entity Verifications.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property ID = new Property(0, byte[].class, "ID", true, "ID");
        public final static Property UserID = new Property(1, byte[].class, "UserID", false, "UserID");
        public final static Property Name = new Property(2, String.class, "Name", false, "Name");
        public final static Property Init = new Property(3, String.class, "Init", false, "Init");
        public final static Property Position = new Property(4, String.class, "Position", false, "Position");
        public final static Property Rank = new Property(5, Integer.class, "Rank", false, "Rank");
        public final static Property Coding = new Property(6, Integer.class, "Coding", false, "Coding");
        public final static Property IsObject = new Property(7, Integer.class, "IsObject", false, "IsObject");
        public final static Property IsOfficer = new Property(8, Integer.class, "IsOfficer", false, "IsOfficer");
        public final static Property Discipline = new Property(9, byte[].class, "Discipline", false, "Discipline");
        public final static Property Organ = new Property(10, Integer.class, "Organ", false, "Organ");
        public final static Property VerificTime = new Property(11, String.class, "VerificTime", false, "VerificTime");
        public final static Property TakingTime = new Property(12, String.class, "TakingTime", false, "TakingTime");
        public final static Property Clues = new Property(13, String.class, "Clues", false, "Clues");
        public final static Property VerificResult = new Property(14, String.class, "VerificResult", false, "VerificResult");
        public final static Property TakingResult = new Property(15, Integer.class, "TakingResult", false, "TakingResult");
        public final static Property ResultSituation = new Property(16, Integer.class, "ResultSituation", false, "ResultSituation");
        public final static Property Note = new Property(17, String.class, "Note", false, "Note");
        public final static Property AnnexIDStr = new Property(18, byte[].class, "AnnexIDStr", false, "AnnexIDStr");
        public final static Property IsDelete = new Property(19, Integer.class, "isDelete", false, "isDelete");
        public final static Property AddDate = new Property(20, String.class, "AddDate", false, "AddDate");
        public final static Property AddUser = new Property(21, String.class, "AddUser", false, "AddUser");
        public final static Property UpdateDate = new Property(22, String.class, "UpdateDate", false, "UpdateDate");
        public final static Property UpdateUser = new Property(23, String.class, "UpdateUser", false, "UpdateUser");
        public final static Property ObjectSource = new Property(24, Integer.class, "objectSource", false, "objectSource");
        public final static Property ProcessTime = new Property(25, String.class, "ProcessTime", false, "ProcessTime");
        public final static Property Trail = new Property(26, String.class, "Trail", false, "Trail");
    }


    public VerificationsDao(DaoConfig config) {
        super(config);
    }
    
    public VerificationsDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"Verifications\" (" + //
                "\"ID\" BLOB PRIMARY KEY ," + // 0: ID
                "\"UserID\" BLOB," + // 1: UserID
                "\"Name\" TEXT," + // 2: Name
                "\"Init\" TEXT," + // 3: Init
                "\"Position\" TEXT," + // 4: Position
                "\"Rank\" INTEGER," + // 5: Rank
                "\"Coding\" INTEGER," + // 6: Coding
                "\"IsObject\" INTEGER," + // 7: IsObject
                "\"IsOfficer\" INTEGER," + // 8: IsOfficer
                "\"Discipline\" BLOB," + // 9: Discipline
                "\"Organ\" INTEGER," + // 10: Organ
                "\"VerificTime\" TEXT," + // 11: VerificTime
                "\"TakingTime\" TEXT," + // 12: TakingTime
                "\"Clues\" TEXT," + // 13: Clues
                "\"VerificResult\" TEXT," + // 14: VerificResult
                "\"TakingResult\" INTEGER," + // 15: TakingResult
                "\"ResultSituation\" INTEGER," + // 16: ResultSituation
                "\"Note\" TEXT," + // 17: Note
                "\"AnnexIDStr\" BLOB," + // 18: AnnexIDStr
                "\"isDelete\" INTEGER," + // 19: isDelete
                "\"AddDate\" TEXT," + // 20: AddDate
                "\"AddUser\" TEXT," + // 21: AddUser
                "\"UpdateDate\" TEXT," + // 22: UpdateDate
                "\"UpdateUser\" TEXT," + // 23: UpdateUser
                "\"objectSource\" INTEGER," + // 24: objectSource
                "\"ProcessTime\" TEXT," + // 25: ProcessTime
                "\"Trail\" TEXT);"); // 26: Trail
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"Verifications\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Verifications entity) {
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
 
        Integer Coding = entity.getCoding();
        if (Coding != null) {
            stmt.bindLong(7, Coding);
        }
 
        Integer IsObject = entity.getIsObject();
        if (IsObject != null) {
            stmt.bindLong(8, IsObject);
        }
 
        Integer IsOfficer = entity.getIsOfficer();
        if (IsOfficer != null) {
            stmt.bindLong(9, IsOfficer);
        }
 
        byte[] Discipline = entity.getDiscipline();
        if (Discipline != null) {
            stmt.bindBlob(10, Discipline);
        }
 
        Integer Organ = entity.getOrgan();
        if (Organ != null) {
            stmt.bindLong(11, Organ);
        }

        String VerificTime = entity.getVerificTime();
        if (VerificTime != null) {
            stmt.bindString(12, VerificTime);
        }

        String TakingTime = entity.getTakingTime();
        if (TakingTime != null) {
            stmt.bindString(13, TakingTime);
        }
 
        String Clues = entity.getClues();
        if (Clues != null) {
            stmt.bindString(14, Clues);
        }
 
        String VerificResult = entity.getVerificResult();
        if (VerificResult != null) {
            stmt.bindString(15, VerificResult);
        }
 
        Integer TakingResult = entity.getTakingResult();
        if (TakingResult != null) {
            stmt.bindLong(16, TakingResult);
        }
 
        Integer ResultSituation = entity.getResultSituation();
        if (ResultSituation != null) {
            stmt.bindLong(17, ResultSituation);
        }
 
        String Note = entity.getNote();
        if (Note != null) {
            stmt.bindString(18, Note);
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

        String ProcessTime = entity.getProcessTime();
        if (ProcessTime != null) {
            stmt.bindString(26, ProcessTime);
        }
 
        String Trail = entity.getTrail();
        if (Trail != null) {
            stmt.bindString(27, Trail);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Verifications entity) {
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
 
        Integer Coding = entity.getCoding();
        if (Coding != null) {
            stmt.bindLong(7, Coding);
        }
 
        Integer IsObject = entity.getIsObject();
        if (IsObject != null) {
            stmt.bindLong(8, IsObject);
        }
 
        Integer IsOfficer = entity.getIsOfficer();
        if (IsOfficer != null) {
            stmt.bindLong(9, IsOfficer);
        }
 
        byte[] Discipline = entity.getDiscipline();
        if (Discipline != null) {
            stmt.bindBlob(10, Discipline);
        }
 
        Integer Organ = entity.getOrgan();
        if (Organ != null) {
            stmt.bindLong(11, Organ);
        }
 
        String VerificTime = entity.getVerificTime();
        if (VerificTime != null) {
            stmt.bindString(12, VerificTime);
        }

        String TakingTime = entity.getTakingTime();
        if (TakingTime != null) {
            stmt.bindString(13, TakingTime);
        }
 
        String Clues = entity.getClues();
        if (Clues != null) {
            stmt.bindString(14, Clues);
        }
 
        String VerificResult = entity.getVerificResult();
        if (VerificResult != null) {
            stmt.bindString(15, VerificResult);
        }
 
        Integer TakingResult = entity.getTakingResult();
        if (TakingResult != null) {
            stmt.bindLong(16, TakingResult);
        }
 
        Integer ResultSituation = entity.getResultSituation();
        if (ResultSituation != null) {
            stmt.bindLong(17, ResultSituation);
        }
 
        String Note = entity.getNote();
        if (Note != null) {
            stmt.bindString(18, Note);
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

        String ProcessTime = entity.getProcessTime();
        if (ProcessTime != null) {
            stmt.bindString(26, ProcessTime);
        }
 
        String Trail = entity.getTrail();
        if (Trail != null) {
            stmt.bindString(27, Trail);
        }
    }

    @Override
    public byte[] readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0);
    }    

    @Override
    public Verifications readEntity(Cursor cursor, int offset) {
        Verifications entity = new Verifications( //
            cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0), // ID
            cursor.isNull(offset + 1) ? null : cursor.getBlob(offset + 1), // UserID
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // Name
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // Init
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // Position
            cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5), // Rank
            cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6), // Coding
            cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7), // IsObject
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // IsOfficer
            cursor.isNull(offset + 9) ? null : cursor.getBlob(offset + 9), // Discipline
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10), // Organ
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // VerificTime
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // TakingTime
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // Clues
            cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14), // VerificResult
            cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15), // TakingResult
            cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16), // ResultSituation
            cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17), // Note
            cursor.isNull(offset + 18) ? null : cursor.getBlob(offset + 18), // AnnexIDStr
            cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19), // isDelete
            cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20), // AddDate
            cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21), // AddUser
            cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22), // UpdateDate
            cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23), // UpdateUser
            cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24), // objectSource
            cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25), // ProcessTime
            cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26) // Trail
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Verifications entity, int offset) {
        entity.setID(cursor.isNull(offset + 0) ? null : cursor.getBlob(offset + 0));
        entity.setUserID(cursor.isNull(offset + 1) ? null : cursor.getBlob(offset + 1));
        entity.setName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setInit(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setPosition(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setRank(cursor.isNull(offset + 5) ? null : cursor.getInt(offset + 5));
        entity.setCoding(cursor.isNull(offset + 6) ? null : cursor.getInt(offset + 6));
        entity.setIsObject(cursor.isNull(offset + 7) ? null : cursor.getInt(offset + 7));
        entity.setIsOfficer(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setDiscipline(cursor.isNull(offset + 9) ? null : cursor.getBlob(offset + 9));
        entity.setOrgan(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
        entity.setVerificTime(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setTakingTime(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setClues(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setVerificResult(cursor.isNull(offset + 14) ? null : cursor.getString(offset + 14));
        entity.setTakingResult(cursor.isNull(offset + 15) ? null : cursor.getInt(offset + 15));
        entity.setResultSituation(cursor.isNull(offset + 16) ? null : cursor.getInt(offset + 16));
        entity.setNote(cursor.isNull(offset + 17) ? null : cursor.getString(offset + 17));
        entity.setAnnexIDStr(cursor.isNull(offset + 18) ? null : cursor.getBlob(offset + 18));
        entity.setIsDelete(cursor.isNull(offset + 19) ? null : cursor.getInt(offset + 19));
        entity.setAddDate(cursor.isNull(offset + 20) ? null : cursor.getString(offset + 20));
        entity.setAddUser(cursor.isNull(offset + 21) ? null : cursor.getString(offset + 21));
        entity.setUpdateDate(cursor.isNull(offset + 22) ? null : cursor.getString(offset + 22));
        entity.setUpdateUser(cursor.isNull(offset + 23) ? null : cursor.getString(offset + 23));
        entity.setObjectSource(cursor.isNull(offset + 24) ? null : cursor.getInt(offset + 24));
        entity.setProcessTime(cursor.isNull(offset + 25) ? null : cursor.getString(offset + 25));
        entity.setTrail(cursor.isNull(offset + 26) ? null : cursor.getString(offset + 26));
     }
    
    @Override
    protected final byte[] updateKeyAfterInsert(Verifications entity, long rowId) {
        return entity.getID();
    }
    
    @Override
    public byte[] getKey(Verifications entity) {
        if(entity != null) {
            return entity.getID();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Verifications entity) {
        return entity.getID() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
