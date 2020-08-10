package com.tradestock.cityfalcom.Networking;

public class RegistrationResponse {
    private static final String Accept = "application/json";
    private static final String Authorization = "0cd6ee5f4b19d976a379032d97b40cccb846256471e0346541af061927271a3f1b5837739e6fa2c8c703ecda79a064eb4529ed8aaa6adbfc14ac527f444f4430";
    private static String lang = "en";

    public static String getAccept() {
        return Accept;
    }

//    public void setAccept(String accept) {
//        Accept = accept;
//    }

    public static String getAuthorization() {
        return Authorization;
    }

//    public void setAuthorization(String authorization) {
//        Authorization = authorization;
//    }
//
    public static String getLang() {
        return lang;
    }

    public static void setLang(String langg) {
        lang = langg;
    }
}
