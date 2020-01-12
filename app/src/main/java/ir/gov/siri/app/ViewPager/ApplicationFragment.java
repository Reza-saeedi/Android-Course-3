package ir.gov.siri.app.ViewPager;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import ir.gov.siri.app.R;

public class ApplicationFragment extends Fragment {


    public  static final  String EXTRA_TEXT="text";

    public String extra;

    public static ApplicationFragment getInstance(String text)
    {
        ApplicationFragment myFragment= new ApplicationFragment(text);

        Bundle bundle=new Bundle();
        bundle.putString(EXTRA_TEXT,text);

        myFragment.setArguments(bundle);

        return myFragment;
    }

    public ApplicationFragment(String extra) {
        this.extra = extra;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_application,null);

        TextView textView=view.findViewById(R.id.tv_fragment_name);
        if(getArguments()!=null)
            textView.setText( getArguments().getString(EXTRA_TEXT,""));


        return view;
    }
}

