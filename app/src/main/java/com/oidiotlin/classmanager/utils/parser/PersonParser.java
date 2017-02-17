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

import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_BIRTHDAY;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_DORMITORY;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_GENDER;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_NAME;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_NATIVE_PROVINCE;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PARTICIPATION;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PERSON;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PHONE_NUMBER;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_PINYIN;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_POSITION;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_NODE_STUDENT_NUMBER;
import static com.oidiotlin.classmanager.utils.system.Constant.XML_ROOT;

/**
 * Created by OIdiot on 2017/2/17.
 * Project: ClassManager
 */

public class PersonParser implements IPersonParser {

    private final String TAG = "PersonParser";

    @Override
    public List<Person> parse(InputStream inputStream) throws Exception {
        List<Person> personList = null;
        Person person = null;
        XmlPullParser xmlPullParser = Xml.newPullParser();
        xmlPullParser.setInput(inputStream, "UTF-8");

        try {
            int eventType = xmlPullParser.getEventType();   // get tag/event

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    // Event - Document's Starting.
                    case XmlPullParser.START_DOCUMENT:
                        personList = new ArrayList<>();
                        break;
                    // Event - Tag's Starting
                    case XmlPullParser.START_TAG:
                        Log.i(TAG, "parse: " + xmlPullParser.getName());
                        if (xmlPullParser.getName().equals(XML_NODE_PERSON))
                            person = new Person();
                        else if (xmlPullParser.getName().equals(XML_NODE_STUDENT_NUMBER)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setStudentNumber(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_NAME)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setName(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_PINYIN)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setPinyin(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_GENDER)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setGender(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_NATIVE_PROVINCE)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setNativeProvince(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_DORMITORY)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setDormitory(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_BIRTHDAY)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setBirthday(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_PHONE_NUMBER)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setPhoneNumber(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_POSITION)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setPosition(xmlPullParser.getText());
                            }
                        } else if (xmlPullParser.getName().equals(XML_NODE_PARTICIPATION)) {
                            eventType = xmlPullParser.next();
                            if (person != null) {
                                person.setParticipation(Integer.parseInt(xmlPullParser.getText()));
                            }
                        }
                        break;
                    // Event - Tag's Ending.
                    case XmlPullParser.END_TAG:
                        if (personList != null &&
                                xmlPullParser.getName().equals(XML_NODE_PERSON)) {
                            personList.add(person);
                            person = null;
                        }
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return personList;
    }

    @Override
    public String serialize(List<Person> persons) throws Exception {
        StringWriter stringWriter = new StringWriter();
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlSerializer xmlSerializer = factory.newSerializer();
            xmlSerializer.setOutput(stringWriter);

            xmlSerializer.startDocument("UTF-8", true);

            xmlSerializer.startTag(null, XML_ROOT);
            for (Person person : persons) {
                xmlSerializer.startTag(null, XML_NODE_PERSON);

                xmlSerializer.startTag(null, XML_NODE_NAME);
                xmlSerializer.text(person.getName());
                xmlSerializer.endTag(null, XML_NODE_NAME);

                xmlSerializer.startTag(null, XML_NODE_STUDENT_NUMBER);
                xmlSerializer.text(person.getStudentNumber());
                xmlSerializer.endTag(null, XML_NODE_STUDENT_NUMBER);

                xmlSerializer.startTag(null, XML_NODE_PINYIN);
                xmlSerializer.text(person.getPinyin());
                xmlSerializer.endTag(null, XML_NODE_PINYIN);

                xmlSerializer.startTag(null, XML_NODE_GENDER);
                xmlSerializer.text(person.getGender());
                xmlSerializer.endTag(null, XML_NODE_GENDER);

                xmlSerializer.startTag(null, XML_NODE_NATIVE_PROVINCE);
                xmlSerializer.text(person.getNativeProvince());
                xmlSerializer.endTag(null, XML_NODE_NATIVE_PROVINCE);

                xmlSerializer.startTag(null, XML_NODE_DORMITORY);
                xmlSerializer.text(person.getDormitory());
                xmlSerializer.endTag(null, XML_NODE_DORMITORY);

                xmlSerializer.startTag(null, XML_NODE_BIRTHDAY);
                xmlSerializer.text(person.getBirthday());
                xmlSerializer.endTag(null, XML_NODE_BIRTHDAY);

                xmlSerializer.startTag(null, XML_NODE_PHONE_NUMBER);
                xmlSerializer.text(person.getPhoneNumber());
                xmlSerializer.endTag(null, XML_NODE_PHONE_NUMBER);

                xmlSerializer.startTag(null, XML_NODE_POSITION);
                xmlSerializer.text(person.getPosition());
                xmlSerializer.endTag(null, XML_NODE_POSITION);

                xmlSerializer.startTag(null, XML_NODE_PARTICIPATION);
                xmlSerializer.text(String.valueOf(person.getParticipation()));
                xmlSerializer.endTag(null, XML_NODE_PARTICIPATION);

                xmlSerializer.endTag(null, XML_NODE_PERSON);
            }
            xmlSerializer.endTag(null, XML_ROOT);

            xmlSerializer.endDocument();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringWriter.toString();
    }
}
