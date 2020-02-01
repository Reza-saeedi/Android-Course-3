package ir.gov.siri.app.DatabaseManager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteHelper extends SQLiteOpenHelper {

    static String DBNAME="AndroidCourse";
    static int DBVERSION=2;
    static  String  ContactTable="contact";
    static String ContactID="id";
    static  String ContactName="name";
    static  String ContactUrl="url";
    static String ContactPhone="phone";
    public SqliteHelper(@Nullable Context context) {
        super(context, DBNAME, null, DBVERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ContactTable+" " +
                "("+ContactID+" integer ,"+ContactName+" Text,"+ContactUrl+" Text,"+ContactPhone+" Text);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ContactTable+";");
        db.execSQL("create table "+ContactTable+" " +
                "("+ContactID+" integer ,"+ContactName+" Text,"+ContactUrl+" Text,"+ContactPhone+" Text);");
    }
}
