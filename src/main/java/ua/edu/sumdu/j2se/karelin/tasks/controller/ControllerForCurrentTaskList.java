package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

import java.time.LocalDateTime;

public class ControllerForCurrentTaskList extends Controller{
    public ControllerForCurrentTaskList(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
        return super.process(taskList);
    }
}
