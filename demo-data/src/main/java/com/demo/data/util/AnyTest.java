package com.demo.data.util;


import lombok.extern.slf4j.Slf4j;
import org.springframework.util.DigestUtils;

@Slf4j
public class AnyTest {


    public static void main(String[] args) throws Exception{
        log.info("md5={}", md5("60f6d092-f860-43fe-b6d0-92f86063fe6b"));
    }


    public static String md5(String text) throws Exception {
        //加密后的字符串
        String encodeStr = DigestUtils.md5DigestAsHex(text.getBytes());

        return encodeStr;
    }


}
