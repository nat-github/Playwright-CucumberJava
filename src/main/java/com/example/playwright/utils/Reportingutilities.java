package com.example.playwright.utils;

import com.epam.reportportal.listeners.LogLevel;
import com.epam.reportportal.service.ReportPortal;

import java.io.File;
import java.util.Date;

public class Reportingutilities {

    public static void reportUtilLog(String message){
        ReportPortal.emitLog(message, LogLevel.INFO.name(),new Date());
    }
    public static void reportUtilLog(String message, File file){
        ReportPortal.emitLog(message, LogLevel.INFO.name(),new Date(),file);
    }

}
