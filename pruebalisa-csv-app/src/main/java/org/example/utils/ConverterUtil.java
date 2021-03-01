package org.example.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConverterUtil {

    public static Long stringToLong(String value) {
        try {
            return Long.parseLong(value.trim());
        }catch (Exception e){
            log.debug(e.getMessage());
            return null;
        }
    }

    public static Integer stringToInteger(String value) {
        try {
            return Integer.parseInt(value.trim());
        } catch (Exception e) {
            log.debug(e.getMessage());
            return null;
        }
    }
}