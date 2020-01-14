package ir.gov.siri.app.DownloadManager;

import android.graphics.Bitmap;

public interface DownloadDelegate {


    void onImageLoadedInThread(Bitmap bitmap);
    void onImageLoaded(Bitmap bitmap);
}
