package com.nokia.utils;

public class Formatter {

    public static void printException(String message) {
        System.out.println("\033[91m" + message + "\033[0m");
    }

    public static void printSuccessfullExecution(String message) {
        System.out.println("\033[92m" + message + "\033[0m");
    }
}
