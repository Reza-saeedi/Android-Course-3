package ir.gov.siri.app.DownloadManager;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DownloadManager {

    DownloadDelegate downloadDelegate;

    static String imageUrl="https://cdn.vox-cdn.com/thumbor/V1lOFhoifgorg5qOnR8sPXyYvQ0=/0x0:2040x1560/1200x800/filters:focal(857x617:1183x943)/cdn.vox-cdn.com/uploads/chorus_image/image/65088839/Android_logo_stacked__RGB_.5.jpg";

    public static Bitmap DownloadImage(final Context context, final DownloadDelegate delegate) {

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {

                    // Looper.prepare();
                    //Toast.makeText(context,"Start Download",Toast.LENGTH_LONG).show();

                    Log.e("Android Course Logs", "Start Download");
                    URL url = new URL(imageUrl);

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    //httpURLConnection.setRequestMethod("GET");
                    //httpURLConnection.setRequestProperty("header", "value");
                    int response = httpURLConnection.getResponseCode();
                    Bitmap image;
                    if (response == HttpURLConnection.HTTP_OK) {
                        InputStream inputStream=httpURLConnection.getInputStream();
                        image= BitmapFactory.decodeStream(inputStream);
                        delegate.onImageLoadedInThread(image);
                    }
                    Log.e("Android Course Logs", "finish Download :"+response);



                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        myThread.start();
        return  null;

    }

    public  void downloadImageAsyncTask(DownloadDelegate downloadDelegate,Context context)
    {
        if(isNetworkConnected(context)) {
            this.downloadDelegate = downloadDelegate;
            FileDownloader fileDownloader = new FileDownloader();
            fileDownloader.execute(imageUrl);
        }
    }


    class  FileDownloader extends AsyncTask<String,Integer,Bitmap>
    {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Bitmap doInBackground(String... string) {
            Bitmap image=null;
            try {

               publishProgress(10);
                Log.e("Android_Course_Logs", "Start Download");
                URL url = new URL(string[0]);


                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                int response = httpURLConnection.getResponseCode();
                publishProgress(20);
                if (response == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream=httpURLConnection.getInputStream();
                    image= BitmapFactory.decodeStream(inputStream);
                }
                Log.e("Android_Course_Logs", "finish Download :"+response);
                publishProgress(100);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return image;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            downloadDelegate.onImageLoaded(bitmap);

        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            Log.e("Android_Course_Logs","onProgressUpdate :"+values[0]);
        }
    }


    public static boolean isNetworkConnected(Context context) {
        final ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (cm != null) {
            if (Build.VERSION.SDK_INT < 23) {
                final NetworkInfo ni = cm.getActiveNetworkInfo();

                if (ni != null) {
                    return (ni.isConnected() && (ni.getType() == ConnectivityManager.TYPE_WIFI || ni.getType() == ConnectivityManager.TYPE_MOBILE));
                }
            } else {
                final Network n = cm.getActiveNetwork();

                if (n != null) {
                    final NetworkCapabilities nc = cm.getNetworkCapabilities(n);

                    return (nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) || nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI));
                }
            }
        }

        return false;
    }


}
