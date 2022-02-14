package org.techtown.gnsassn2bygn;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.techtown.audio.gilneungsassignment2.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    Button button;
    TextView textView;
    TextView textView2;

    @Override
    protected void attachBaseContext(Context newBase) {
        String languageToLoad = PreferenceManager.getString(newBase, "locale");
        super.attachBaseContext(BaseContextWrapper.wrap(newBase, LocaleUtil.getLocaleToString(languageToLoad)));
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onResume() {
        super.onResume();
        // 현재 기기 언어 설정값 가져오기
        Log.e("LocaleTest-onResume", getApplicationContext().getResources().getConfiguration().locale.toString());
        Log.e("LocaleTest-onResume", this.getResources().getConfiguration().locale.toString());
        textView2.setText(PreferenceManager.getString(this, "locale"));
        Log.e("LocaleTest-onResumestr", getString(R.string.japan));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

}