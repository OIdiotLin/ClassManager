package com.oidiotlin.classmanager.utils.network;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.oidiotlin.classmanager.utils.parser.Person;
import com.oidiotlin.classmanager.utils.parser.PersonParser;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static com.oidiotlin.classmanager.utils.system.Constant.API_GET_PERSON_LIST;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK_FAIL;
import static com.oidiotlin.classmanager.utils.system.Constant.GET_PERSONS_LIST_TASK_SUCCESS;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_API;
import static com.oidiotlin.classmanager.utils.system.Constant.SERVER_RESPONSE_TIMEOUT;

/**
 * Created by OIdiot on 2017/2/17.
 * Project: ClassManager
 */

public class GetPersonListTask implements Runnable {
    private final String TAG = "GetPersonListTask";
    private Context context;
    private Handler handler;

    public GetPersonListTask(Context context, Handler handler) {
        this.context = context;
        this.handler = handler;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(SERVER_API + API_GET_PERSON_LIST);
            Log.i(TAG, "run: " + url.toString());
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setConnectTimeout(SERVER_RESPONSE_TIMEOUT);
            InputStream inputStream = connection.getInputStream();

            List<Person> persons = (new PersonParser()).parse(inputStream);

            if (persons != null) {
                Message msg = new Message();
                msg.what = GET_PERSONS_LIST_TASK;
                msg.arg1 = GET_PERSONS_LIST_TASK_SUCCESS;
                msg.obj = persons;
                handler.sendMessage(msg);
            } else throw new NullPointerException();

        } catch (Exception e) {
            Message msg = new Message();
            msg.what = GET_PERSONS_LIST_TASK;
            msg.arg1 = GET_PERSONS_LIST_TASK_FAIL;
            handler.sendMessage(msg);
            e.printStackTrace();
        }
    }
}
