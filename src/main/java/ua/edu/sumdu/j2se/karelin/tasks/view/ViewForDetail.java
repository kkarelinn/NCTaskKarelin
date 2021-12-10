package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.ParseData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

public class ViewForDetail implements View {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("Please, choose task number to receive detail information: ");
        int index = ParseData.getListIdFromLine(list);
        Task t = list.getTask(index);
        System.out.println("---------------------------");
        System.out.println("Detail information about task " + t.getTitle());
        System.out.println("Title: " + t.getTitle());
        System.out.println("TIME to start: " + ((t.isRepeated())? " not set" : t.getTime()));
        System.out.println("START time: " + ((t.isRepeated())? t.getStartTime() :" not set"));
        System.out.println("END time: " + ((t.isRepeated())? t.getEndTime() :" not set"));
        System.out.println("time INTERVAL: " + ((t.isRepeated())? t.getRepeatInterval()/60+"min" :" not set"));
        System.out.println((t.isActive())? "task is ACTIVE" : "task is NOT ACTIVE");
        System.out.println("---------------------------");
        System.out.print("Do you want to change current task? (y/n): ");
        boolean choise = ParseData.getBooFromLine();
        if (!choise){
            return Controller.MAIN_MENU_ACTION;
        }

        System.out.println("Choose, what you want to do:");
        System.out.println("1. Set new TITLE.");
        System.out.println("2. Set new TIME to launch.");
        System.out.println("3. Set new START time, END time and INTERVAL.");
        System.out.println("4. Set ACTIVITY.");
        System.out.print("Enter your choice: ");
        int action = ParseData.getActionFromLine();
        switch (action){
            case 1 : {
                try {
                    System.out.print("Enter new title: ");
                    String str = ParseData.getNameFromLine();
                    t.setTitle(str);
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 : {
                t.setTime(ParseData.getTimeFromLine());
                break;
            }
            case 3 : {
                System.out.print("Enter START time in format YYYY-MM-DD HH:MM:SS : ");
                LocalDateTime start = ParseData.getTimeFromLine();
                System.out.print("Enter END time in format YYYY-MM-DD HH:MM:SS : ");
                LocalDateTime end = ParseData.getTimeFromLine();
                int interval = ParseData.getIntFromLine(start, end);
                t.setTime(start, end, interval);
                break;
            }
            case 4 : {
                System.out.print("Make task ACTIVE? (y/n) : ");
               t.setActive(ParseData.getBooFromLine());
               break;
            }
        }
        System.out.println("Change successful");
        printInfo(list);

    return Controller.MAIN_MENU_ACTION;
    }
}
