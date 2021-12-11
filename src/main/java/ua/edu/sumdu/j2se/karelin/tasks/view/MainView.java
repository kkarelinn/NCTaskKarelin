package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class MainView implements View {
    boolean start = true;

    @Override
    public int printInfo(AbstractTaskList list) {

        System.out.println();
        if (start) {
            System.out.println("-----It's your \"TASK'S CALENDAR\"----\n");
            start = false;
        }

        if (list.size() > 0) {
            System.out.println("You have SOME TASKS in you calendar. What would you like to do?");
        } else {
            System.out.println("You have NO ONE TASKS in you calendar. What would you like to do?");
        }
        System.out.println("1. View ALL tasks from your tasks list");
        System.out.println("2. View a list of scheduled tasks until the end of the CURRENT week");
        System.out.println("3. Add NEW task to your tasks list");
        System.out.println("4. REMOVE some task from your tasks list");
        System.out.println("5. Set an ALERT time before running scheduled tasks");
        System.out.println("6. EXIT program\n");
        System.out.print("Please enter your choice (number): ");
        int choose = 0;
        choose = ParseData.getActionFromLine(6);
        return choose;
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
        return -1;
    }

    @Override
    public boolean getAnswer() {
        return false;
    }

    @Override
    public void printMessage(String message) {
    }


}
