package com.example.giggle.oschina2.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DoubleConverter;
import com.thoughtworks.xstream.converters.basic.FloatConverter;
import com.thoughtworks.xstream.converters.basic.IntConverter;
import com.thoughtworks.xstream.converters.basic.LongConverter;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Giggle on 2015-11-27.
 */
public class XmlUtils {

    private final static String TAG = XmlUtils.class.getSimpleName();

    /**
     * 将xml数据流转换为bean
     *
     * @param type
     * @param inputStream
     * @param <T>
     * @return
     */
    public static <T> T toBean(Class<T> type, InputStream inputStream) {
        XStream xStream = new XStream(new DomDriver("UTF-8"));

        xStream.ignoreUnknownElements();

        xStream.registerConverter(new MyIntConverter());
        xStream.registerConverter(new MyFloatConverter());
        xStream.registerConverter(new MyDoubleConverter());
        xStream.registerConverter(new MyLongConverter());
        xStream.processAnnotations(type);
        T obj = null;
        try {
            obj = (T) xStream.fromXML(inputStream);
        } catch (Exception e) {
            TLog.log(TAG, "解析XML文件异常" + e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

    private static class MyIntConverter extends IntConverter {

        @Override
        public Object fromString(String str) {
            int value;
            try {
                value = (Integer) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyLongConverter extends LongConverter {
        @Override
        public Object fromString(String str) {
            long value;
            try {
                value = (Long) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyFloatConverter extends FloatConverter {
        @Override
        public Object fromString(String str) {
            float value;
            try {
                value = (Float) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }

    private static class MyDoubleConverter extends DoubleConverter {
        @Override
        public Object fromString(String str) {
            double value;
            try {
                value = (Double) super.fromString(str);
            } catch (Exception e) {
                value = 0;
            }
            return value;
        }

        @Override
        public String toString(Object obj) {
            return super.toString(obj);
        }
    }
}