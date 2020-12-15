package org.pkuse2020grp4.pkusporteventsbackend.utils;

import org.springframework.web.context.request.NativeWebRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

public class WebUtils {
    public static String RequestToText(NativeWebRequest nativeWebRequest) throws Exception
    {
        HttpServletRequest request = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
        // 把reqeust的body读取到StringBuilder
        BufferedReader reader = request.getReader();
        StringBuilder sb = new StringBuilder();
        char[] buf = new char[1024];
        int rd;
        while((rd = reader.read(buf)) != -1){
            sb.append(buf, 0, rd);
        }
        return sb.toString();
    }
}
