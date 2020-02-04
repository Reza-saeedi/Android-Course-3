package ir.gov.siri.app.Media;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ir.gov.siri.app.R;

public class VideoActivity extends AppCompatActivity {

    final int REQUEST_VIDEO_CAPTURE=1;
    VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        videoView=findViewById(R.id.VideoView);
        Button button = findViewById(R.id.btn_openCamera);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            Uri videoUri = data.getData();
            videoView.setVideoURI(videoUri);


            MediaController mediaController=new MediaController(this);
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);
            videoView.start();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
