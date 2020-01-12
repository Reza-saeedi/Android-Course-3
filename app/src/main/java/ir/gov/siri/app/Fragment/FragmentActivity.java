package ir.gov.siri.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.List;

import ir.gov.siri.app.R;

public class FragmentActivity extends AppCompatActivity {


    List<MyFragment> myFragments;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

        Button addFragment=findViewById(R.id.btn_add_fragment);


        addFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myFragments==null)
                    myFragments=new ArrayList<>();


                MyFragment myFragment1=MyFragment.getInstance("Fragment 1");
                myFragments.add(myFragment1);
                FragmentManager fragmentManager=getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.fragment_view,myFragment1).addToBackStack("test").commit();

                MyFragment myFragment=MyFragment.getInstance("Fragment 2");
                myFragments.add(myFragment);
                fragmentManager.beginTransaction().add(R.id.fragment_view,myFragment).addToBackStack("test").commit();
                fragmentManager.beginTransaction().setCustomAnimations(R.anim.open_animation,R.anim.open_animation);

                fragmentManager.beginTransaction().remove(myFragments.get(1)).commit();
                myFragments.remove(1);
                //??
                //fragmentManager.beginTransaction().re(R.id.fragment_view,new MyFragment()).addToBackStack("test").commit();
            }
        });






    }
}
