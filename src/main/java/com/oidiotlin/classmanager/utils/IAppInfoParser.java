package com.oidiotlin.classmanager.utils;

import java.io.InputStream;
import java.util.List;

/**
 * Created by OIdiot on 2017/2/5.
 * Project: ClassManager
 */

public interface IAppInfoParser {
    /**
     * parse InputStream, access the info list
     * @param inputStream
     * @return
     * @throws Exception
     */
    public List<AppInfo> parse(InputStream inputStream) throws Exception;

    /**
     * serialize object set - AppInfo, access xml in string format
     * @param infos
     * @return
     * @throws Exception
     */
    public String serialize(List<AppInfo> infos) throws Exception;
}