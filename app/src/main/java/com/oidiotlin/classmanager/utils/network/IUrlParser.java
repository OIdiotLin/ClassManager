package com.oidiotlin.classmanager.utils.network;

import java.io.InputStream;
import java.net.URL;
import java.util.List;

/**
 * Created by OIdiot on 2017/2/10.
 * Project: ClassManager
 */

public interface IUrlParser {
    /**
     * parse inputStream, access the url list
     * @param inputStream
     * @return
     * @throws Exception
     */
    public List<URL> parse(InputStream inputStream) throws Exception;

    /**
     * serialize object set - URL, access xml in String format
     * @param urls
     * @return
     * @throws Exception
     */
    public String serialize(List<URL> urls) throws Exception;
}
