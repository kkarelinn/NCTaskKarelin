package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class ViewForAllert implements View {

    @Override
    public int printInfo(AbstractTaskList list) {

        return Controller.MAIN_MENU_ACTION;
    }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
    }


    @Override
    public Task getInfo(Task task) {
       return null;
    }

    @Override
    public int getInfo() {
        System.out.print("Please, enter notification time in minutes: ");
        int min = ParseData.getIntervalFromLine();
        return min;
           }


    @Override
    public boolean getAnswer() {
        return false;
    }

    @Override
    public void printMessage(String message) {

    }


}

