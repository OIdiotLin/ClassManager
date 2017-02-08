package com.oidiotlin.classmanager.utils;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
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

        try {
            int eventType = xmlPullParser.getEventType();   // get tag/event

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // Event - Document's Starting.
                    case XmlPullParser.START_DOCUMENT:
                        appInfoList = new ArrayList<AppInfo>();
                        break;
                    // Event - Tag's Starting
                    case XmlPullParser.START_TAG:
                        if (xmlPullParser.getName().equals(Constant.APPINFO))
                            info = new AppInfo();
                        else if (xmlPullParser.getName().equals(Constant.APPINFO_APPVER)) {
                            eventType = xmlPullParser.next();
                            info.setAppVer(Integer.parseInt(xmlPullParser.getText()));
                        } else if (xmlPullParser.getName().equals(Constant.APPINFO_DBVER)) {
                            eventType = xmlPullParser.next();
                            info.setDbVer(Integer.parseInt(xmlPullParser.getText()));
                        }
                        break;
                    // Event - Tag's Ending.
                    case XmlPullParser.END_TAG:
                        if (xmlPullParser.getName().equals(Constant.APPINFO)) {
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

            for (AppInfo info : infos) {
                xmlSerializer.startTag(null, Constant.APPINFO);

                xmlSerializer.startTag(null, Constant.APPINFO_APPVER);
                xmlSerializer.text(String.valueOf(info.getAppVer()));
                xmlSerializer.endTag(null, Constant.APPINFO_APPVER);

                xmlSerializer.startTag(null, Constant.APPINFO_DBVER);
                xmlSerializer.text(String.valueOf(info.getDbVer()));
                xmlSerializer.endTag(null, Constant.APPINFO_DBVER);

                xmlSerializer.endTag(null, Constant.APPINFO);
            }

            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
