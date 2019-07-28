package cn.imhtb.ad.utils;

import cn.imhtb.ad.exception.AdException;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;

import java.util.Date;

/**
 * @author PinTeh
 * @date 2019/7/28
 */
public class CommonUtils {

    private static String[] parsePatterns = {
            "yyyy-MM-dd","yyyy/MM/dd","yyyy.MM.dd"
    };

    public static String md5(String value){
        return DigestUtils.md5Hex(value).toUpperCase();
    }

    public static Date String2Date(String data) throws AdException {

        try {
            return DateUtils.parseDate(data, parsePatterns);
        } catch (Exception e){
            throw new AdException(e.getMessage()) ;
        }
    }
}
