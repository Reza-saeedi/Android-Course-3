package ir.gov.siri.app.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

import ir.gov.siri.app.R;

public class FragmentPreferences extends Fragment {

    CheckBox checkBox;
    CheckBox checkBoxDB;
    EditText fileName;

    public static FragmentPreferences getInstance()
    {
        return  new FragmentPreferences();
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(R.layout.preferences_fragment,container,false);


         checkBox=root.findViewById(R.id.cb_my_check_box);
        checkBoxDB=root.findViewById(R.id.cb_DB);

         fileName=root.findViewById(R.id.et_file_name);

        Picasso.get().load("https://cdn.vox-cdn.com/thumbor/V1lOFhoifgorg5qOnR8sPXyYvQ0=/0x0:2040x1560/1200x800/filters:focal(857x617:1183x943)/cdn.vox-cdn.com/uploads/chorus_image/image/65088839/Android_logo_stacked__RGB_.5.jpg")
                .into((ImageView) root.findViewById(R.id.iv_download));


        SharedPreferences preferences=null;
        if(getContext()!=null) {
            preferences = getContext().getSharedPreferences("SecureData", Context.MODE_PRIVATE);
            fileName.setText(preferences.getString("FileName",""));
            checkBox.setChecked(preferences.getBoolean("cacheData",false));
            checkBoxDB.setChecked(preferences.getBoolean("enableORM",false));
        }



        Button save=root.findViewById(R.id.btn_save_preference);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text=fileName.getText().toString();
                boolean checked=checkBox.isChecked();
                SharedPreferences preferences=null;
                if(getContext()!=null) {
                    preferences = getContext().getSharedPreferences("SecureData", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("FileName",text);
                    editor.putBoolean("cacheData",checked);
                    editor.putBoolean("enableORM",checkBoxDB.isChecked());
                    editor.apply();


                }

                Toast.makeText(getContext(),"Data Saved",Toast.LENGTH_SHORT).show();


            }
        });


        return root;
    }
}
