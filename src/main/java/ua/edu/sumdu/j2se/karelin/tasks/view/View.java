package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public interface View {
    int printInfo(AbstractTaskList list);
    void printCalendar(Map<LocalDateTime, Set<Task>> map);
    Task getInfo(Task task);
    int getInfo();
    boolean getAnswer();
    void printMessage(String message);
}