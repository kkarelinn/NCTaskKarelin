package ua.edu.sumdu.j2se.karelin.tasks;

import ua.edu.sumdu.j2se.karelin.tasks.controller.MainController;
import ua.edu.sumdu.j2se.karelin.tasks.model.*;
import ua.edu.sumdu.j2se.karelin.tasks.view.MainView;

import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        MainController mvc = new MainController(new ArrayTaskList(), new MainView());
        mvc.start();



    }
    public static void printMap(Map<LocalDateTime, Set<Task>> map){
        for(Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            LocalDateTime date = entry.getKey();
            StringBuilder sb = new StringBuilder(date.toString()).append(": ");
            Set<Task> tasks = entry.getValue();
            for (Task t: tasks) {
                sb.append(t.getTitle()).append(", ");
            }
            System.out.println(sb.substring(0, sb.length()-2));
        }
    }

    public static void writeBinary(String text, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            System.out.println("Начинаем работу врайтера....");
            oos.writeUTF(text);
            } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
    public static void readBinary(String text, File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Начинаем работу ридера....");
            String s = ois.readUTF();
            System.out.println("-----------");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
}

