package ir.gov.siri.app.ViewPager2;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.List;

import ir.gov.siri.app.Contact.Contact;
import ir.gov.siri.app.Contact.ContactDelegate;
import ir.gov.siri.app.Contact.ContactManager;
import ir.gov.siri.app.R;

public class ViewPager2Activity extends AppCompatActivity implements ContactDelegate {

    ViewPager2 viewPager2;
    BazaarViewpager2Adapter bazaarViewPager2Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2);
        viewPager2=findViewById(R.id.vp_bazaar);

        ContactManager contactManager=new ContactManager();
        List<Contact> contacts= contactManager.getContactFromProvider(this);
        bazaarViewPager2Adapter=new BazaarViewpager2Adapter(contacts,this);
        viewPager2.setAdapter(bazaarViewPager2Adapter);

        TabLayout tabLayout=findViewById(R.id.tl_bazaar);
        //tabLayout.setupWithViewPager(viewPager2);
        viewPager2.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        TabLayoutMediator tabLayoutMediator=new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText("item :"+ position);
            }
        });
        tabLayoutMediator.attach();
    }

    @Override
    public void onClick(View v, Contact contact) {

    }

    @Override
    public boolean onLongClick(View v, Contact contact) {
        return false;
    }
}
