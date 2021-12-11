package ua.edu.sumdu.j2se.karelin.tasks.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ParseData {
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static LocalDateTime getTimeFromLine(){
        while (true) {
            try {
                return LocalDateTime.parse(br.readLine(), formatter);
            } catch (Exception e) {
                System.out.print("Wrong date time. Enter again: ");
            }
        }

    }
    public static int getIntervalFromLine(LocalDateTime start, LocalDateTime end){
        while (true) {
            try {
                System.out.print("Enter time INTERVAL in min : ");
                int interval = Integer.parseInt(br.readLine())*60;
                if (start.isBefore(end)
                        && interval <= (end.toEpochSecond(ZoneOffset.UTC) - start.toEpochSecond(ZoneOffset.UTC))){
                    return interval;
                } else System.out.print("Wrong interval. Enter again: ");

            } catch (Exception e) {
                System.out.print("Wrong interval. Enter again: ");
            }
        }
    }
    public static int getIntervalFromLine(){
        while (true) {
            try {
                int interval = Integer.parseInt(br.readLine());
                if (interval > 0){
                    return interval;
                } else System.out.print("Wrong time in minutes. Enter again: ");

            } catch (Exception e) {
                System.out.print("Wrong time in minutes. Enter again: ");
            }
        }
    }
    public static boolean getBooFromLine(){
        while (true) {
            try {
                String temp = br.readLine();
                if (temp.matches("[yn]{1}")) {
                    return temp.equals("y");
                }
            } catch (IOException e) {
                System.out.println("Some mistake");
                e.printStackTrace();
            }
            System.out.print("Wrong parameter. Enter again: ");
        }
    }
    public static String getNameFromLine(){
        while (true) {
            try {
                String str = br.readLine();
                if (!str.isEmpty()){
                    return str;
                }
                System.out.print("Line is empty. Enter again: ");
            } catch (IOException e) {
                System.out.print("Wrong parameter. Enter again: ");
            }
        }
    }
       public static int getActionFromLine(int size) {
        while (true) {
            try {
                int index = Integer.parseInt(br.readLine());
                if (index > 0 && index <= size) {
                   return index;
                } else {
                    System.out.print("Wrong choice. Please, try again: ");

                }
            } catch (Exception e) {
                System.out.print("Wrong choice. Please, try again: ");
            }
        }
    }
}
