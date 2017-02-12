package com.oidiotlin.classmanager.utils.network;

import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RETURN_URL_NODE;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_ROOT;

/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public class UrlParser implements IUrlParser {
    @Override
    public List<URL> parse(InputStream inputStream) throws Exception {
        List<URL> urls = null;
        URL url;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");

        try {
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        urls = new ArrayList<>();
                        break;
                    case XmlPullParser.START_TAG:
                        // nothing here
                        if (xmlPullParser.getName().equals(SERVER_RETURN_URL_NODE)) {
                            eventType = xmlPullParser.next();
                            url = new URL(xmlPullParser.getText());
                            urls.add(url);
                            url = null;
                        }
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return urls;
    }

    @Override
    public String serialize(List<URL> urls) throws Exception {
        StringWriter stringWriter = new StringWriter();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            xmlSerializer.setOutput(stringWriter);
            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, XML_ROOT);
            for (URL url : urls) {
                xmlSerializer.startTag(null, SERVER_RETURN_URL_NODE);
                xmlSerializer.text(String.valueOf(url.getPath()));
                xmlSerializer.endTag(null, SERVER_RETURN_URL_NODE);
            }
            xmlSerializer.endTag(null, XML_ROOT);

            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
