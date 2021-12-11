package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Tasks;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;


import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;

public class ControllerForCalendar extends Controller {
    public ControllerForCalendar(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        try {
            view.printCalendar(Tasks.calendar(taskList, LocalDateTime.now()
                    , LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).with(LocalTime.MIN)));
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return MAIN_MENU_ACTION;

    }


}
