package ir.gov.siri.app.Media;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import ir.gov.siri.app.R;

import static ir.gov.siri.app.ApplicationLoader.getContext;

public class CameraActivity extends AppCompatActivity {

    final int REQUEST_IMAGE_CAPTURE = 1;
    final int REQUEST_IMAGE_CAPTURE_FILE = 2;
    final int REQUEST_IMAGE_CROP = 3;
    final int REQUEST_GALLERY = 4;
    final int REQUEST_IMAGE = 5;
    ImageView imageView;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        Button button = findViewById(R.id.btn_openCamera);
        imageView = findViewById(R.id.iv_camera);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

            }
        });

        Button buttonGallery = findViewById(R.id.btn_openGallery);
        buttonGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent galleryIntent = new Intent(Intent.ACTION_PICK);
                galleryIntent.setType("image/*");
               // galleryIntent.putExtra("return-data", true);
                startActivityForResult(galleryIntent, REQUEST_GALLERY);

            }
        });


        Button buttonFile = findViewById(R.id.btn_openCameraFile);

        buttonFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                try {
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(createImageFile()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                startActivityForResult(intent, REQUEST_IMAGE_CAPTURE_FILE);

            }
        });

        Button buttonALL = findViewById(R.id.btn_openALL);

        buttonALL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openImageIntent();

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_IMAGE_CAPTURE) {


                Bitmap bitmap=(Bitmap) data.getExtras().get("data");
                imageView.setImageBitmap(bitmap);

                Intent intent=new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(data.getData(),"image/*");
              /*  intent.putExtra("aspectX",1);
                intent.putExtra("aspectY",1);*/
                startActivityForResult(intent,REQUEST_IMAGE_CROP);
            }else if (requestCode == REQUEST_IMAGE_CAPTURE_FILE) {
                Bitmap bitmap= BitmapFactory.decodeFile(currentPhotoPath);
                imageView.setImageBitmap(bitmap);
                galleryAddPic();
            }else if (requestCode == REQUEST_IMAGE_CROP) {
                if(data.getAction()!=null)
                    imageView.setImageBitmap(BitmapFactory.decodeFile(data.getAction()));
            }else if(requestCode== REQUEST_GALLERY)
            {
                if (data != null) {
                    final Uri imageUri = data.getData();
                     InputStream imageStream=null;
                    try {
                        imageStream = getContentResolver().openInputStream(imageUri);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);

                    int height=selectedImage.getHeight()*512/selectedImage.getWidth();
                    Bitmap photo=Bitmap.createScaledBitmap(selectedImage,512,height,false);


                    imageView.setImageBitmap(photo);

                }
            }else if(REQUEST_IMAGE==requestCode)
            {
                Uri uri = null;
                String path = null;
                Bitmap selectedImage = null;
                if (data != null) {
                    uri = data.getData();
                    path = getRealPathFromURI(uri);
                }
                if (uri == null) {
                    uri = Uri.fromFile(new File(currentPhotoPath));
                    path = uri.getPath();
                }
                File imgFile = new File(path);
                selectedImage = BitmapFactory.decodeFile(imgFile.getAbsolutePath());

              /*  int height=selectedImage.getHeight()*512/selectedImage.getWidth();
                Bitmap photo=Bitmap.createScaledBitmap(selectedImage,512,height,false);*/
                imageView.setImageBitmap(selectedImage);
            }
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp ="test";// new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }


    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }

    private void openImageIntent() {
        // Determine Uri of camera image to save.
        // Camera.
        final List<Intent> cameraIntents = new ArrayList<Intent>();
        final Intent captureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        final PackageManager packageManager = getContext().getPackageManager();
        final List<ResolveInfo> listCam = packageManager.queryIntentActivities(captureIntent, 0);
        for (ResolveInfo res : listCam) {
            final Intent intent = new Intent(captureIntent);
            intent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            try {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(createImageFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            cameraIntents.add(intent);
        }
        // Filesystem.
        final Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryIntent.setType("image/*");
        galleryIntent.putExtra("return-data", true);
        // Chooser of filesystem options.
        final Intent chooserIntent = Intent.createChooser(galleryIntent, "Select");

        // Add the camera options.
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, cameraIntents.toArray(new Parcelable[cameraIntents.size()]));
        startActivityForResult(chooserIntent, REQUEST_IMAGE);
    }


    private String getRealPathFromURI(Uri contentURI) {
        String result;
        Cursor cursor = getContext().getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            result = contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            result = cursor.getString(idx);
            cursor.close();
        }
        return result;
    }

}
