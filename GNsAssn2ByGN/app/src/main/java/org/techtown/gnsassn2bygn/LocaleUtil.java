package org.techtown.gnsassn2bygn;

import java.util.Locale;

public class LocaleUtil {

    public static Locale getLocaleToString(String localeStr){
        Locale locale = null;
        if(localeStr.toLowerCase().equals("ja_jp")){
            locale = Locale.JAPAN;
        } else if(localeStr.toLowerCase().equals("ko_kr")){
            locale = Locale.KOREA;
        } else {
            locale = Locale.US;
        }
        return locale;
    }
}
