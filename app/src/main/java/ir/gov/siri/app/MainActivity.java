package ir.gov.siri.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import ir.gov.siri.app.Contact.ContactActivity;
import ir.gov.siri.app.Fragment.FragmentActivity;
import ir.gov.siri.app.ViewPager.ViewPagerActivity;
import ir.gov.siri.app.ViewPager2.ViewPager2Activity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    DrawerLayout drawerLayout;
    Toast exitToast;
    Long time = 0L;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.dl_main);
        NavigationView navigationView = findViewById(R.id.nv_drawer);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.intent_menu) {
                    Intent intent = new Intent(MainActivity.this, IntentActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.coordinator_menu) {
                    Intent intent = new Intent(MainActivity.this, CoordinatorActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.fragment_menu) {
                    Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.view_pager_menu) {
                    Intent intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.view_pager2_menu) {
                    Intent intent = new Intent(MainActivity.this, ViewPager2Activity.class);
                    startActivity(intent);
                } else if (item.getItemId() == R.id.browser_activity_menu) {
                    Intent intent = new Intent(MainActivity.this, BrowserActivity.class);
                    startActivity(intent);
                }
                return false;
            }
        });


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        //drawerLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);

        if (getIntent().hasExtra(android.content.Intent.EXTRA_TEXT)) {
            String text = getIntent().getStringExtra(android.content.Intent.EXTRA_TEXT);
            TextView textView = findViewById(R.id.tv_extra);
            textView.setText(text);
        }

        exitToast = Toast.makeText(MainActivity.this, R.string.exit_toast_message, Toast.LENGTH_SHORT);


        Button btn_toast = findViewById(R.id.btn_toast);
        btn_toast.setOnClickListener(this);

        Button btn_snackbar = findViewById(R.id.btn_snackbar);
        btn_snackbar.setOnClickListener(this);

        Button btn_dialog = findViewById(R.id.btn_dialog);
        btn_dialog.setOnClickListener(this);

        Button btnContactList = findViewById(R.id.btn_contact_activity);
        btnContactList.setOnClickListener(this);

        Button btnMenu = findViewById(R.id.btn_menu);
        btnMenu.setOnClickListener(this);
        registerForContextMenu(btnMenu);

       /* FrameLayout frameLayout=new FrameLayout(this);
        frameLayout.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        map.setLayoutParams(new FrameLayout.LayoutParams(1000, ViewGroup.LayoutParams.MATCH_PARENT));
        frameLayout.addView(map);
        setContentView(frameLayout);*/

        View view = getLayoutInflater().inflate(R.layout.activity_intent, null);
        view.findViewById(R.id.btn_login);
        initBroadcastReceiver();

    }



    private void initBroadcastReceiver() {
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context, "Main PowerBroadcastReceiver", Toast.LENGTH_SHORT).show();
                Button btn_toast = findViewById(R.id.btn_toast);
                btn_toast.setText("PowerBroadcastReceiver");
            }
        };
        LocalBroadcastManager.getInstance(this).registerReceiver(broadcastReceiver, new IntentFilter("android.intent.action.ACTION_POWER_CONNECTED"));


    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_toast) {
            Toast toast = Toast.makeText(MainActivity.this, R.string.toast_message, Toast.LENGTH_LONG);
            toast.setGravity(Gravity.CENTER, 0, 0);
            //toast.setView(getLayoutInflater().inflate(R.layout.activity_intent,null));
            toast.show();
        } else if (v.getId() == R.id.btn_snackbar) {
            Snackbar snackbar = Snackbar.make(findViewById(R.id.ll_main), R.string.toast_message, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.OK, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
            snackbar.show();
        } else if (v.getId() == R.id.btn_dialog) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this, R.style.AppTheme_Dialog);
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
                    Toast toast = Toast.makeText(MainActivity.this, "which : " + which, Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            dialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast toast = Toast.makeText(MainActivity.this, "which : " + which, Toast.LENGTH_LONG);
                    toast.show();
                }
            });

            dialog.setNeutralButton(R.string.app_name, null);
            dialog.show();
        } else if (v.getId() == R.id.btn_contact_activity) {
            Intent intent = new Intent(MainActivity.this, ContactActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.btn_menu) {
            PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
            popupMenu.getMenuInflater().inflate(R.menu.main_menu, popupMenu.getMenu());
            popupMenu.show();
        }


    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else {

            if (exitToast.getView().isShown() || System.currentTimeMillis() - time < 10000)
                super.onBackPressed();
            else {
                time = System.currentTimeMillis();
                exitToast.show();
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item1) {
            startActionMode(new ActionMode.Callback() {
                @Override
                public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                    getMenuInflater().inflate(R.menu.main_menu, menu);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(broadcastReceiver);
    }
}
