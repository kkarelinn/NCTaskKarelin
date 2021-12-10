package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.controller.ControllerForAddTask;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.ParseData;

import java.time.LocalDateTime;

public class ViewForAddTask implements View {

    @Override
    public int printInfo(AbstractTaskList list) {

        Task newTask = null;

        System.out.println();
        System.out.println("Entering data of new Task to list");

        System.out.print("Please, enter Task name: ");
        String title = ParseData.getNameFromLine();

        System.out.print("Do Task has a time interval (y/n)? :");
        boolean hasInterval = ParseData.getBooFromLine();

        if (!hasInterval) {
            System.out.print("Please, enter the start time of the task in format YYYY-MM-DD HH:MM : ");
            LocalDateTime time = ParseData.getTimeFromLine();
            newTask = new Task(title, time);
        } else {
            System.out.print("Please, enter the START of interval of the task in format YYYY-MM-DD HH:MM : ");
            LocalDateTime start = ParseData.getTimeFromLine();
            System.out.print("Please, enter the END of interval of the task in format YYYY-MM-DD HH:MM : ");
            LocalDateTime end = ParseData.getTimeFromLine();
            int interval = ParseData.getIntFromLine(start, end);
            newTask = new Task(title, start, end, interval);
        }
        System.out.print("The Task is active? (y/n): ");
        boolean isActive = ParseData.getBooFromLine();
        newTask.setActive(isActive);
        ControllerForAddTask.addTask(list, newTask);

        System.out.print("Would you like to ADD one more Task? (y/n): ");
        boolean repeat = ParseData.getBooFromLine();
        if (repeat) {
            printInfo(list);
        }
        return Controller.MAIN_MENU_ACTION;

    }

}
