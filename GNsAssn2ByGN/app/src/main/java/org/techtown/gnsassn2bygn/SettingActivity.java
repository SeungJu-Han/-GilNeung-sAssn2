package org.techtown.gnsassn2bygn;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.techtown.audio.gilneungsassignment2.R;

import java.util.ArrayList;
import java.util.Locale;

public class SettingActivity extends AppCompatActivity {
    RadioButton radioButton1;
    RadioButton radioButton2;
    RadioButton radioButton3;
    SharedPreferences sharedPreferences;
    String locale;
    ArrayList<String> locales;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //버전 확인 후 sharedpreferences에 locale키 값의 value 값을 가져옵니다.
        //값이 없을 경우 기본언어 설정
        locale = PreferenceManager.getString(this, "locale");
        if(TextUtils.isEmpty(locale)){
            locale = Locale.KOREA.toString();
        }
        radioButton1 = findViewById(R.id.radioButton1);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton1.setText(getString(R.string.korea));
        radioButton2.setText(getString(R.string.japan));
        radioButton3.setText(getString(R.string.usa));

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        rg.setOnCheckedChangeListener((radioGroup, i) -> {
            if (i == R.id.radioButton1) {
                locale = "ko_KR";
            } else if (i == R.id.radioButton2) {
                locale = "ja_JP";
            } else if (i == R.id.radioButton3) {
                locale = "en_US";
            }
        });


        if (locale.equals(Locale.KOREA.toString())) {
            rg.check(R.id.radioButton1); //라디오버튼 초기값 설정
        } else if (locale.equals(Locale.JAPAN.toString())) {
            rg.check(R.id.radioButton2);
        } else {
            rg.check(R.id.radioButton3);
        }

        Button okButton = (Button) findViewById(R.id.button2);
        locales = new ArrayList<>();

        locales.add(getString(R.string.korea));
        locales.add(getString(R.string.japan));
        locales.add(getString(R.string.usa));
        okButton.setOnClickListener(v -> {
            Toast.makeText(this, "언어 변경이 제대로 되려면 앱을 껏다 켜야 합니다.", Toast.LENGTH_SHORT).show();
            PreferenceManager.setString(okButton.getContext(), "locale", locale);
            finish();
        });
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        String languageToLoad = PreferenceManager.getString(newBase, "locale");
        super.attachBaseContext(BaseContextWrapper.wrap(newBase, LocaleUtil.getLocaleToString(languageToLoad)));
    }

}
