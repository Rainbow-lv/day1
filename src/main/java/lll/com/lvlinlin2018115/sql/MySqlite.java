package lll.com.lvlinlin2018115.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySqlite extends SQLiteOpenHelper {
    public MySqlite( Context context) {
        super(context, "news.db", null, 1);
    }
    /**
     * news_id : 13811
     * news_summary : 6月17—20日，“2016成都深港澳台嘉年华会”(简称嘉年华会)将在成都世纪城国际会展中心举办。其主办方励展华博借力旗
     * news_title : 深港澳台千里连线，嘉年华会今夏入川
     * pic_url : http://f.expoon.com/sub/news/2016/01/21/887844_230x162_0.jpg
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE new_table(personid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "news_id VARCHAR(20)," +
                    "news_summary VARCHAR(20)," +
                    "news_title VARCHAR(20)," +
                    "pic_url VARCHAR(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
