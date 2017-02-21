package com.oidiotlin.classmanager.utils.parser;

import java.io.InputStream;
import java.util.List;

/**
 * Created by OIdiot on 2017/2/20.
 * Project: ClassManager
 */

public interface IEventParser {
    /**
     * parse InputStream, access the events list
     * @param inputStream
     * @return
     * @throws Exception
     */
    public List<Event> parse(InputStream inputStream) throws Exception;

    /**
     * serialize object set - Event, access xml in string format
     * @param events
     * @return
     * @throws Exception
     */
    public String serialize(List<Event> events) throws Exception;
}
