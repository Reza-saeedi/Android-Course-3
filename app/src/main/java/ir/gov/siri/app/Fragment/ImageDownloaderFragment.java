package ir.gov.siri.app.Fragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.gov.siri.app.DownloadManager.DownloadDelegate;
import ir.gov.siri.app.DownloadManager.DownloadManager;
import ir.gov.siri.app.R;

public class ImageDownloaderFragment extends Fragment implements DownloadDelegate {

    Handler handler;
    ImageView imageView;

    public static  ImageDownloaderFragment getInstance()
    {
        return  new ImageDownloaderFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_image_downloader,container,false);

        handler=new Handler();

        Button btnDownload=view.findViewById(R.id.btn_t_download);



        btnDownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.mipmap.ic_launcher);
                DownloadManager.DownloadImage(getContext(),ImageDownloaderFragment.this);
            }
        });


        Button btnADownloader=view.findViewById(R.id.btn_a_download);



        btnADownloader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setImageResource(R.mipmap.ic_launcher);
               new DownloadManager().downloadImageAsyncTask(ImageDownloaderFragment.this,getContext());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(R.mipmap.ic_launcher);
                    }
                },1000*5);
            }
        });

        imageView=view.findViewById(R.id.iv_download);
        imageView.setImageResource(R.mipmap.ic_launcher);


        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.M && getContext()!=null)
        {
           if( getContext().checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED || getContext().checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED)
           {
               String[] permissions=new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE};
              requestPermissions(permissions,1);
           }
        }








        return view;
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onImageLoadedInThread(final Bitmap bitmap) {
        handler.post(new Runnable() {
            @Override
            public void run() {

                imageView.setImageBitmap(bitmap);
            }
        });



    }

    @Override
    public void onImageLoaded(final Bitmap bitmap) {
        imageView.setImageBitmap(bitmap);

    }
}
