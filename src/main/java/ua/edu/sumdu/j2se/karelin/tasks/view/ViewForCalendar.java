package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.Set;

public class ViewForCalendar implements View {
    @Override
    public int printInfo(AbstractTaskList list) {
        return -1;
    }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
        System.out.println("Calendar to the end of the CURRENT week");
        if (map.size() == 0) {
            System.out.println("the Calendar is empty");
        }

        for (Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            LocalDateTime date = entry.getKey();
            StringBuilder sb = new StringBuilder(new StringBuilder(date.toLocalDate()
                    + " " + date.toLocalTime().truncatedTo(ChronoUnit.SECONDS)).append(" - "));
            Set<Task> tasks = entry.getValue();
            for (Task t : tasks) {
                sb.append(t.getTitle()).append(", ");
            }
            System.out.println(sb.substring(0, sb.length() - 2));
        }
    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }

    @Override
    public int getInfo() {
        return 0;
    }

    @Override
    public boolean getAnswer() {
        return false;
    }

    @Override
    public void printMessage(String message) {
    }


}


