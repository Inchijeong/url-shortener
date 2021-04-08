package com.uss.utils;

import java.net.URL;
import java.net.URLConnection;

import org.springframework.stereotype.Component;

@Component
public class URLUtil {

    public boolean valid(String url) {
        try {
            URL connectionUrl = new URL(url);
            URLConnection conn = connectionUrl.openConnection();
            conn.connect();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
	
	public static String removeParameter(String localURL) {
        return localURL.substring(0, localURL.lastIndexOf("/"));
    }
}
