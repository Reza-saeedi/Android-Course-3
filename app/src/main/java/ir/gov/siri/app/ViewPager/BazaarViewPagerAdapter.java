package ir.gov.siri.app.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.google.android.material.tabs.TabLayout;

import java.util.HashMap;

import ir.gov.siri.app.Fragment.FragmentPreferences;
import ir.gov.siri.app.Fragment.ImageDownloaderFragment;
import ir.gov.siri.app.Fragment.MyFragment;
import ir.gov.siri.app.R;

public class BazaarViewPagerAdapter extends FragmentStatePagerAdapter {

    public BazaarViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        myFragments=new HashMap<>();
    }

    HashMap<Integer,Fragment> myFragments;

    @NonNull
    @Override
    public Fragment getItem(int position) {


        if(position==0)
        {
            return ImageDownloaderFragment.getInstance();
        }else
        if(position==1)
        {
            return FragmentPreferences.getInstance();
        }else if(position%3==0){
            if(myFragments.get(position)!=null)
                return myFragments.get(position);
            MyFragment myFragment=MyFragment.getInstance("View pager Item :"+position);
            myFragments.put(position,myFragment);
            return myFragment;

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


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        //super.destroyItem(container, position, object);
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
