package study.zgq.com.androidstudy.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class MyContentProvider extends ContentProvider {
    private final static String AUTHORITY = "com.android.zgq.provider";
    private final static int STUDENT_URI_CODE = 0;
    private static UriMatcher sUriMatcher;
    private Context mContext;
    private SQLiteDatabase mDataBase;
    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sUriMatcher.addURI(AUTHORITY,"student",STUDENT_URI_CODE);
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        mDataBase = new DBOpenHelper(mContext).getWritableDatabase();
        return false;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        int uriType = sUriMatcher.match(uri);
        long row;

        switch (uriType) {
            case STUDENT_URI_CODE:
                row = mDataBase.insert(DBOpenHelper.DATABASE_STUDENT_TABLE_NAME,null, values);
                break;
            default:
                throw new IllegalArgumentException("UnSupport Uri : " + uri);
        }

        if(row > -1) {
            mContext.getContentResolver().notifyChange(uri,null);
            return ContentUris.withAppendedId(uri, row);
        }

        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
