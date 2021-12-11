package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class ViewForAddTask implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        return -1;
    }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
    }

    //  @Override
    public Task getInfo(Task task) {
        Task newTask;
        System.out.println();
        System.out.println("Entering data of new Task to list");

        System.out.print("Please, enter Task name: ");
        String title = ParseData.getNameFromLine();

        System.out.print("Do Task has a time interval (y/n)? :");
        boolean hasInterval = ParseData.getBooFromLine();

        if (!hasInterval) {
            System.out.print("Please, enter the start time of the task in format YYYY-MM-DD HH:MM:SS : ");
            LocalDateTime time = ParseData.getTimeFromLine();
            newTask = new Task(title, time);
        } else {
            System.out.print("Please, enter the START of interval of the task in format YYYY-MM-DD HH:MM:SS : ");
            LocalDateTime start = ParseData.getTimeFromLine();
            System.out.print("Please, enter the END of interval of the task in format YYYY-MM-DD HH:MM:SS : ");
            LocalDateTime end = ParseData.getTimeFromLine();
            System.out.print("Please, enter the time interval in minutes: ");
            int interval = ParseData.getIntervalFromLine(start, end);
            newTask = new Task(title, start, end, interval);
        }
        System.out.print("The Task is active? (y/n)");
        boolean isActive = ParseData.getBooFromLine();
        newTask.setActive(isActive);

        return newTask;
    }

    //  @Override
    public int getInfo() {
        return 0;
    }

    @Override
    public boolean getAnswer() {
        System.out.print("Would you like to ADD one more Task? (y/n): ");
        boolean repeat = ParseData.getBooFromLine();
        return repeat;
    }

    @Override
    public void printMessage(String message) {
    }


}
