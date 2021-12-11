package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

public class ViewForDetail implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("Please, choose task number to receive detail information: ");
        int index = ParseData.getActionFromLine(list.size());
       return index;
         }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
    }


    @Override
    public Task getInfo(Task task) {
        System.out.println("---------------------------");
        System.out.println("Detail information about task " + task.getTitle());
        System.out.println("Title: " + task.getTitle());
        System.out.println("TIME to start: " + ((task.isRepeated())? " not set" : task.getTime().toLocalDate()
                +" "+task.getTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES)));

        System.out.println("START time: " + ((task.isRepeated())? task.getStartTime().toLocalDate()
                +" "+task.getStartTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES) :" not set"));

        System.out.println("END time: " + ((task.isRepeated())? task.getEndTime().toLocalDate()
                +" "+task.getEndTime().toLocalTime().truncatedTo(ChronoUnit.MINUTES) :" not set"));

        System.out.println("time INTERVAL: " + ((task.isRepeated())? task.getRepeatInterval()/60+"min" :" not set"));
        System.out.println((task.isActive())? "task is ACTIVE" : "task is NOT ACTIVE");
        System.out.println("---------------------------");

        return null;
    }

    @Override
    public int getInfo() {
        return 0;
    }

    @Override
    public boolean getAnswer() {
        System.out.print("Do you want to change current task? (y/n): ");
        return ParseData.getBooFromLine();
    }

    @Override
    public void printMessage(String message) {

    }


}
