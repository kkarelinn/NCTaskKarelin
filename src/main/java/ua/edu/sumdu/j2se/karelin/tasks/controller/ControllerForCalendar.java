package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.model.Tasks;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;


import java.time.LocalDateTime;
import java.util.Set;
import java.util.SortedMap;

public class ControllerForCalendar extends Controller{
    public ControllerForCalendar(View view, int actionToPerform) {
         super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
     return super.process(taskList);

    }
    public static SortedMap<LocalDateTime, Set<Task>> getCalendar(Iterable<Task> tasks, LocalDateTime start,
                                                                  LocalDateTime end) throws CloneNotSupportedException {
        return Tasks.calendar(tasks, start, end);
    }
}
