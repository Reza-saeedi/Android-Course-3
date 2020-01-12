package ir.gov.siri.app.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.google.android.material.tabs.TabLayout;

import ir.gov.siri.app.Fragment.MyFragment;
import ir.gov.siri.app.R;

public class BazaarViewPagerAdapter extends FragmentPagerAdapter {

    public BazaarViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if(position%2==0)
        {
            return MyFragment.getInstance("View pager Item :"+position);
        }else
        {
            return ApplicationFragment.getInstance("View pager Item :"+position);
        }

    }

    @Override
    public int getCount() {
        return 10;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return "position :"+position;
    }


    void setTabView(LayoutInflater inflater, TabLayout tabLayout)
    {
       for (int i = 0; i < tabLayout.getTabCount(); i++) {
           View view=inflater.inflate(R.layout.tab_item,null);
           //view.findViewById(R.)
           view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT));

           tabLayout.getTabAt(i).setCustomView(view);
        }

    }

    public void onTabSelected(TabLayout.Tab tab)
    {
        TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_item);
        textView.setTextColor(0xff00ff00);

    }
    public void onTabUnSelected(TabLayout.Tab tab)
    {
        TextView textView=tab.getCustomView().findViewById(R.id.tv_tab_item);
        textView.setTextColor(textView.getResources().getColor(R.color.color_white));

    }

}
