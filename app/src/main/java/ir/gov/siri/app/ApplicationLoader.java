package ir.gov.siri.app;

import android.app.Application;
import android.content.Context;

import org.greenrobot.greendao.database.Database;

import ir.gov.siri.app.Contact.DaoMaster;
import ir.gov.siri.app.Contact.DaoSession;

public class ApplicationLoader extends Application {

    private static DaoSession daoSession;

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        // regular SQLite database
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "Contact-db");
        Database db = helper.getWritableDb();

        // encrypted SQLCipher database
        // note: you need to add SQLCipher to your dependencies, check the build.gradle file
        // DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "notes-db-encrypted");
        // Database db = helper.getEncryptedWritableDb("encryption-key");

        daoSession = new DaoMaster(db).newSession();
        context=getApplicationContext();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }

    public static Context getContext() {
        return context;
    }
}
