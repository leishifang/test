package com.example.giggle.oschina2.util;

import java.util.Date;
import java.util.TimeZone;

/**
 * Created by Giggle on 2015-12-12.
 */
public class TimeZoneUtils {

    /**
     * 判断用户时区是否是东八区
     *
     * @return
     */
    public static Boolean isInEightZone() {
        if (TimeZone.getDefault() == TimeZone.getTimeZone("GTM+08")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据时区转换时间
     *
     * @param preDate
     * @param preTimeZone
     * @param targetTimeZone
     * @return
     */
    public static Date transformTime(Date preDate, TimeZone preTimeZone, TimeZone targetTimeZone) {
        Date date = null;

        if (preDate != null) {
            int offSet = preTimeZone.getOffset(preDate.getTime()) - targetTimeZone.getOffset(preDate.getTime());
            date = new Date(preDate.getTime() - offSet);
        }

        return date;
    }
}
