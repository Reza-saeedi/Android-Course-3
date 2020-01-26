package ir.gov.siri.app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import java.io.File;

public class FileManager {



    //data/data/package name/cache
    public static File getCacheFile(Context context) {

        return context.getCacheDir();
    }


    //Storage/Android/data/package name/cache
    public static File getExternalCacheDir(Context context) {
        //String p=Environment.getDownloadCacheDirectory().getAbsolutePath();
        return context.getExternalCacheDir();
    }

    //Storage
    public static File getExternalDir(Context context) {
        //String p=Environment.getDownloadCacheDirectory().getAbsolutePath();

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M )
        {
            if(context.checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED || context.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
            {
                return  getCacheFile(context);
            }
        }
        return Environment.getExternalStorageDirectory();
    }

    //Storage/PICTURES
    public static File getExternalPublicDir(Context context) {
        //String p=Environment.getDownloadCacheDirectory().getAbsolutePath();
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
    }
}
