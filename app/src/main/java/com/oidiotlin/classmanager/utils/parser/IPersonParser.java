package com.oidiotlin.classmanager.utils.parser;

import java.io.InputStream;
import java.util.List;

/**
 * Created by OIdiot on 2017/2/17.
 * Project: ClassManager
 */

public interface IPersonParser {
    /**
     * parse InputStream, access the person list
     * @param inputStream
     * @return
     * @throws Exception
     */
    public List<Person> parse(InputStream inputStream) throws Exception;

    /**
     * serialize object set - Person, access xml in string format
     * @param persons
     * @return
     * @throws Exception
     */
    public String serialize(List<Person> persons) throws Exception;
}
