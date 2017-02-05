package com.oidiotlin.classmanager.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class AppInfoParser implements IAppInfoParser {
    @Override
    public List<AppInfo> parse(InputStream inputStream) throws Exception {
        List<AppInfo> appInfoList = null;
        AppInfo info = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");

        int eventType = xmlPullParser.getEventType();   // get tag/event

        while (eventType != XmlPullParser.END_DOCUMENT) {
            switch (eventType) {
                // Event - Document's Starting.
                case XmlPullParser.START_DOCUMENT:
                    appInfoList = new ArrayList<AppInfo>();
                    break;
                // Event - Tag
                case XmlPullParser.START_TAG:
                    if (xmlPullParser.getName().equals(Constant.APPINFO_))
            }
        }
    }
}
