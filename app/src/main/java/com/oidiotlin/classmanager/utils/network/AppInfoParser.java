package com.oidiotlin.classmanager.utils.network;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.APPINFO_NEW_FEATURES;
import static com.oidiotlin.classmanager.utils.system.Constant.APPINFO_VERSION_CODE;
import static com.oidiotlin.classmanager.utils.system.Constant.APPINFO_VERSION_NAME;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_ROOT;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public class AppInfoParser implements IAppInfoParser {
    private final String TAG = "AppInfoParser";

    @Override
    public List<AppInfo> parse(InputStream inputStream) throws Exception {
        List<AppInfo> appInfoList = null;
        AppInfo info = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");

        try {
            int eventType = xmlPullParser.getEventType();   // get tag/event

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // Event - Document's Starting.
                    case XmlPullParser.START_DOCUMENT:
                        appInfoList = new ArrayList<>();
                        break;
                    // Event - Tag's Starting
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "parse: " + xmlPullParser.getName());
                        if (xmlPullParser.getName().equals(XML_ROOT))
                            info = new AppInfo();
                        else if (xmlPullParser.getName().equals(APPINFO_VERSION_CODE)) {
                            eventType = xmlPullParser.next();
                            info.setVersionCode(Integer.parseInt(xmlPullParser.getText()));
                        } else if (xmlPullParser.getName().equals(APPINFO_VERSION_NAME)) {
                            eventType = xmlPullParser.next();
                            info.setVersionName(xmlPullParser.getText());
                        } else if (xmlPullParser.getName().equals(APPINFO_NEW_FEATURES)) {
                            eventType = xmlPullParser.next();
                            info.setNewFeatures(xmlPullParser.getText());
                        }
                        break;
                    // Event - Tag's Ending.
                    case XmlPullParser.END_TAG:
                        if (xmlPullParser.getName().equals(XML_ROOT)) {
                            appInfoList.add(info);
                            info = null;
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appInfoList;
    }

    @Override
    public String serialize(List<AppInfo> infos) throws Exception {
        StringWriter stringWriter = new StringWriter();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            xmlSerializer.setOutput(stringWriter);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, XML_ROOT);
            for (AppInfo info : infos) {
                xmlSerializer.startTag(null, APPINFO_VERSION_CODE);
                xmlSerializer.text(String.valueOf(info.getVersionCode()));
                xmlSerializer.endTag(null, APPINFO_VERSION_CODE);

                xmlSerializer.startTag(null, APPINFO_VERSION_NAME);
                xmlSerializer.text(String.valueOf(info.getVersionName()));
                xmlSerializer.endTag(null, APPINFO_VERSION_NAME);

                xmlSerializer.startTag(null, APPINFO_NEW_FEATURES);
                xmlSerializer.text(String.valueOf(info.getNewFeatures()));
                xmlSerializer.endTag(null, APPINFO_NEW_FEATURES);
            }
            xmlSerializer.endTag(null, XML_ROOT);

            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
