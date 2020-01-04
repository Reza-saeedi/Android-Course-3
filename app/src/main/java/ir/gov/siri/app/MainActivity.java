package ir.gov.siri.app;


import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.SearchView;

import com.google.android.material.snackbar.Snackbar;

import ir.gov.siri.app.Contact.ContactActivity;

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

        Button btnContactList=findViewById(R.id.btn_contact_activity);
        btnContactList.setOnClickListener(this);

        Button btnMenu=findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(this);
        registerForContextMenu(btnMenu);

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
        }else if(v.getId()==R.id.btn_contact_activity)
        {
            Intent intent=new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        }else if(v.getId()==R.id.btn_menu)
        {
            PopupMenu popupMenu=new PopupMenu(MainActivity.this,v);
            popupMenu.getMenuInflater().inflate(R.menu.main_menu,popupMenu.getMenu());
            popupMenu.show();
        }

        SearchView s
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item1)
        {
            startActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    getMenuInflater().inflate(R.menu.main_menu,menu);
                    return true;
                }

                @Override
                public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                    return false;
                }

                @Override
                public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                    return false;
                }

                @Override
                public void onDestroyActionMode(ActionMode mode) {

                }
            });
        }
        return super.onOptionsItemSelected(item);
    }
}
