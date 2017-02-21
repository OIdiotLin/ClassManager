package com.oidiotlin.classmanager.utils.parser;

import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_CONTENT;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_DATE;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_NAME;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_PARTICIPATION;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_PLACE;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_EVENT_TIME;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PARTICIPATION;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PERSON;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_ROOT;

/**
 * Created by OIdiot on 2017/2/21.
 * Project: ClassManager
 */

public class EventParser implements IEventParser {
    private final String TAG = "EventParser";

    @Override
    public List<Event> parse(InputStream inputStream) throws Exception {
        List<Event> events = null;
        Event event = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");

        try {
            int eventType = xmlPullParser.getEventType();   // get tag/event

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // Event - Document's Starting.
                    case XmlPullParser.START_DOCUMENT:
                        events = new ArrayList<>();
                        break;
                    // Event - Tag's Starting
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "parse: " + xmlPullParser.getName());
                        if (xmlPullParser.getName().equals(XML_NODE_EVENT))
                            event = new Event();
                        else if (xmlPullParser.getName().equals(XML_NODE_EVENT_NAME)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setName(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_EVENT_DATE)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setDate(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_EVENT_PLACE)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setPlace(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_EVENT_CONTENT)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setContent(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_PARTICIPATION)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setParticipation(Integer.parseInt(xmlPullParser.getText()));
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_EVENT_TIME)) {
                            eventType = xmlPullParser.next();
                            if (event != null) {
                                event.setTime(xmlPullParser.getText());
                            }
                        }
                        break;
                    // Event - Tag's Ending.
                    case XmlPullParser.END_TAG:
                        if (events != null &&
                                xmlPullParser.getName().equals(XML_NODE_PERSON)) {
                            events.add(event);
                            event = null;
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    @Override
    public String serialize(List<Event> events) throws Exception {
        StringWriter stringWriter = new StringWriter();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            xmlSerializer.setOutput(stringWriter);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, XML_ROOT);
            for (Event event : events) {
                xmlSerializer.startTag(null, XML_NODE_EVENT);

                xmlSerializer.startTag(null, XML_NODE_EVENT_NAME);
                xmlSerializer.text(event.getName());
                xmlSerializer.endTag(null, XML_NODE_EVENT_NAME);

                xmlSerializer.startTag(null, XML_NODE_EVENT_DATE);
                xmlSerializer.text(event.getDate());
                xmlSerializer.endTag(null, XML_NODE_EVENT_DATE);

                xmlSerializer.startTag(null, XML_NODE_EVENT_TIME);
                xmlSerializer.text(event.getTime());
                xmlSerializer.endTag(null, XML_NODE_EVENT_TIME);

                xmlSerializer.startTag(null, XML_NODE_EVENT_PLACE);
                xmlSerializer.text(event.getPlace());
                xmlSerializer.endTag(null, XML_NODE_EVENT_PLACE);

                xmlSerializer.startTag(null, XML_NODE_EVENT_PARTICIPATION);
                xmlSerializer.text(String.valueOf(event.getParticipation()));
                xmlSerializer.endTag(null, XML_NODE_EVENT_PARTICIPATION);

                xmlSerializer.endTag(null, XML_NODE_EVENT);
            }
            xmlSerializer.endTag(null, XML_ROOT);

            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
