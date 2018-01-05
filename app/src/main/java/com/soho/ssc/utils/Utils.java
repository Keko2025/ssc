/*
 *  Copyright (C) 2015 MummyDing
 *
 *  This file is part of Leisure( <https://github.com/MummyDing/Leisure> )
 *
 *  Leisure is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *                             ｀
 *  Leisure is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with Leisure.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.soho.ssc.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.text.TextUtils;
import android.widget.Toast;

import com.soho.ssc.MyApplication;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import okhttp3.Callback;
import okhttp3.Request;

/**
 * Created by mummyding on 15-11-13.
 */
public class Utils {

    private static boolean DEBUG = true;
    private static Context mContext = MyApplication.getContext();
    public static InputStream readFileFromRaw(int fileID){
       return mContext.getResources()
               .openRawResource(fileID);
    }

    public static Document getDocmentByIS(InputStream is){
        DocumentBuilderFactory factory= DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc =null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
             doc = builder.parse(is);
             is.close();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc ;
    }

    // convert InputStream to String
    public static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }
    public static String RegexFind(String regex, String string, int start, int end){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        String res = string;
        while (matcher.find()){
            res = matcher.group();
        }
        return res.substring(start, res.length() - end);
    }
    public static String RegexFind(String regex, String string){
        return RegexFind(regex, string, 1, 1);
    }
    public static String RegexReplace(String regex, String string, String replace){
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(string);
        return matcher.replaceAll(replace);
    }
    public static boolean hasString(String str){
        if(str == null || str.equals("")) return false;
        return true;
    }
    public static void showToast(String text){
        Toast.makeText(mContext,text, Toast.LENGTH_SHORT).show();
    }

    public static String getImageHtml(){
        return "<head><style>img{max-width: 320px !important;}</style></head>";
    }


    // Must be called before setContentView()
    public static void changeLanguage(Context context, int lang) {
        String language = null;
        String country = null;

        switch (lang) {
            case 1:
                language = "zh";
                country = "CN";
                break;
            default:
                language = "en";
                country = "US";
                break;
        }

        Locale locale = new Locale(language, country);
        Configuration conf = context.getResources().getConfiguration();
        conf.locale = locale;
        context.getApplicationContext().getResources().updateConfiguration(conf, context.getResources().getDisplayMetrics());
    }

    public static String getVersion() {
        try {
            PackageManager manager = mContext.getPackageManager();
            PackageInfo info = manager.getPackageInfo(mContext.getPackageName(), 0);
            String version = info.versionName;
            return  version;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void getRawHtmlFromUrl(String url, Callback callback) {
        if (callback == null || TextUtils.isEmpty(url)) {
            return ;
        }
        Request.Builder builder = new Request.Builder();
        builder.url(url);
        Request request = builder.build();
        HttpUtil.enqueue(request, callback);
    }
}
