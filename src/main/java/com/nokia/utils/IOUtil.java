package com.nokia.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOUtil {
  Scanner sc = new Scanner(System.in);

  private static IOUtil instance = null;

  public static IOUtil getInstance() {
    if (instance == null) {
      instance = new IOUtil();
    }
    return instance;
  }

  public String getStringInput(String message) {
    try {
      System.out.print(message);
      Scanner sc = new Scanner(System.in);
      return sc.nextLine();
    }
    catch (InputMismatchException e) {
      String exceptionMessage="Input type should be string";
      Formatter.printException(exceptionMessage);
      return getStringInput(message);
    }
  }

  public int getIntegerInput(String message) {
    try {
      System.out.print(message);
      Scanner sc = new Scanner(System.in);
      return sc.nextInt();
    } catch (InputMismatchException e) {
      String exceptionMessage="Input type should be number";
      Formatter.printException(exceptionMessage);
      return getIntegerInput(message);
    }
  }

  public Float getFloatInput(String message) {
    try {
      System.out.print(message);
      Scanner sc = new Scanner(System.in);
      return sc.nextFloat();
    } catch (InputMismatchException e) {
      String exceptionMessage="Input type should be floating number";
      Formatter.printException(exceptionMessage);
      return getFloatInput(message);
    }
  }

  public Double getDoubleInput(String message) {
    try {
      System.out.print(message);
      Scanner sc = new Scanner(System.in);
      return sc.nextDouble();
    } catch (InputMismatchException e) {
      String exceptionMessage="Input type should be Double number";
      Formatter.printException(exceptionMessage);
      return getDoubleInput(message);
    }
  }
}
