package ir.gov.siri.app;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class IntentActivity extends AppCompatActivity implements View.OnClickListener {

    Toast exitToast;
    Long time=0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent);

        if(getIntent().hasExtra(android.content.Intent.EXTRA_TEXT)) {
            String text = getIntent().getStringExtra(android.content.Intent.EXTRA_TEXT);
            TextView textView = findViewById(R.id.tv_extra);
            textView.setText(text);
        }

        exitToast=Toast.makeText(IntentActivity.this,R.string.exit_toast_message,Toast.LENGTH_SHORT);
        Button btn=findViewById(R.id.btn_intent);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://www.divar.ir";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

            }
        });

        Button map=findViewById(R.id.btn_map);
        map.setOnClickListener(this);

        Button btnTextIntent=findViewById(R.id.btn_text_intent);
        btnTextIntent.setOnClickListener(this);

        Button btnLogin=findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
       // btnLogin.setVisibility(View.GONE);




        View view=getLayoutInflater().inflate(R.layout.activity_intent,null);
        view.findViewById(R.id.btn_login);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_map)
        {
            Uri gmmIntentUri = Uri.parse("geo:50,46.414382");
            Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);

            //mapIntent.setPackage("com.google.android.apps.maps");

            startActivity(mapIntent);
        }else if(v.getId()==R.id.btn_text_intent)
        {
            String shareBody = "Here is the share content body";

            Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);

            sharingIntent.setType("text/plain");

            sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject Here");

            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);

            sharingIntent.setPackage("ir.gov.siri.app");


            startActivity(Intent.createChooser(sharingIntent,getResources().getString(R.string.share_using)));

        }else if(v.getId()==R.id.btn_login)
        {
             Intent intent=new Intent(IntentActivity.this,SplashScreenActivity.class);
            EditText editText=findViewById(R.id.et_value);
                intent.putExtra(SplashScreenActivity.EXTRA_TEXT,editText.getText().toString());
                startActivity(intent);
        }

    }

    @Override
    public void onBackPressed() {


        if(exitToast.getView().isShown() || System.currentTimeMillis()-time<10000)
            super.onBackPressed();
        else {
            time=System.currentTimeMillis();
            exitToast.show();
        }

    }



}
