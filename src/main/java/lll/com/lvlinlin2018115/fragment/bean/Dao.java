package lll.com.lvlinlin2018115.fragment.bean;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import lll.com.lvlinlin2018115.sql.MySqlite;

public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context) {
        //创建对象
        MySqlite mySqlite = new MySqlite(context);
        db = mySqlite.getWritableDatabase();
    }

    //写方法
    public long insert(String table, String nullColumnHack, ContentValues values) {
        return db.insert(table, nullColumnHack, values);
    }

    public long delete(String table, String whereClause, String[] whereArgs) {
        return db.delete(table, whereClause, whereArgs);
    }

    public long update(String table, ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(table, values, whereClause, whereArgs);
    }

    public Cursor select(String table, String[] columns, String selection,
                         String[] selectionArgs, String groupBy, String having,
                         String orderBy) {
        return db.query(table, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
}
