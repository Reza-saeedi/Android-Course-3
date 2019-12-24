package ir.gov.siri.app;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Toast exitToast;
    Long time=0L;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(getIntent().hasExtra(android.content.Intent.EXTRA_TEXT)) {
            String text = getIntent().getStringExtra(android.content.Intent.EXTRA_TEXT);
            TextView textView = findViewById(R.id.tv_extra);
            textView.setText(text);
        }

        exitToast=Toast.makeText(MainActivity.this,R.string.exit_toast_message,Toast.LENGTH_SHORT);
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


        Button btn_toast=findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(this);

        Button btn_snackbar=findViewById(R.id.btn_snackbar);
        btn_snackbar.setOnClickListener(this);

        Button btn_dialog=findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);

       /* FrameLayout frameLayout=new FrameLayout(this);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        map.setLayoutParams(new FrameLayout.LayoutParams(1000, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.addView(map);
        setContentView(frameLayout);*/

        View view=getLayoutInflater().inflate(R.layout.activity_main,null);
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
             Intent intent=new Intent(MainActivity.this,SplashScreenActivity.class);
            EditText editText=findViewById(R.id.et_value);
                intent.putExtra(SplashScreenActivity.EXTRA_TEXT,editText.getText().toString());
                startActivity(intent);
        }else if(v.getId()==R.id.btn_toast)
        {
            Toast toast=Toast.makeText(MainActivity.this,R.string.toast_message,Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.setView(getLayoutInflater().inflate(R.layout.activity_main,null));
            toast.show();
        }else if(v.getId()==R.id.btn_snackbar)
        {
            Snackbar snackbar=Snackbar.make(findViewById(R.id.ll_main),R.string.toast_message, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.OK, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            snackbar.show();
        }else if(v.getId()==R.id.btn_dialog)
        {
            AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this,R.style.AppTheme_Dialog);
            dialog.setCancelable(false);
           // dialog.setMessage(R.string.dialog_message);
            dialog.setSingleChoiceItems(R.array.Dialog_list, 0, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

         /*   dialog.setMultiChoiceItems(R.array.Dialog_list, new boolean[10], new DialogInterface.OnMultiChoiceClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which, boolean isChecked) {

                }
            });*/


            dialog.setTitle(R.string.app_name);
            dialog.setPositiveButton(R.string.OK, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast=Toast.makeText(MainActivity.this,"which : "+which,Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast=Toast.makeText(MainActivity.this,"which : "+which,Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            dialog.setNeutralButton(R.string.app_name,null);
            dialog.show();
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
