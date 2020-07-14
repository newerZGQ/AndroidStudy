package study.zgq.com.androidstudy.content;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBOpenHelper extends SQLiteOpenHelper {
    private final static String TAG = "DBOpenHelper";

    private final static String DATABASE_NAME = "com_android_zgq_provider.db";
    public final static String DATABASE_STUDENT_TABLE_NAME = "student";
    private final static int DATABASE_VERSION = 1;

    private Context mContext;

    /**
     * student table
     * @id primary key
     * @name student's name. e.g:peter.
     * @gender student's gender. e.g: 0 male; 1 female.
     * @number student's number. e.g: 201804081702.
     * @score student's score. more than 0 and less than 100. e.g:90.
     * */
    private final static String CREATE_STUDENT_TABLE = "CREATE TABLE IF NOT EXISTS "
            + DATABASE_STUDENT_TABLE_NAME
            + "(id INTEGER PRIMARY KEY,"
            + "name TEXT VARCHAR(20) NOT NULL,"
            + "gender BIT DEFAULT(1),"
            + "number TEXT VARCHAR(12) NOT NULL,"
            + "score INTEGER CHECK(score >= 0 and score <= 100))";

    public DBOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mContext = context;
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        mContext = context;
    }

    public DBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION, errorHandler);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate");
        db.execSQL(CREATE_STUDENT_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade o = " + oldVersion + " , n = " + newVersion);
    }
}