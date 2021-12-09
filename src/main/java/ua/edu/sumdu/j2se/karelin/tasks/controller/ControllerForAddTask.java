package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForAddTask extends Controller{
    public ControllerForAddTask(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {

        return super.process(taskList);
    }

    public static void addTask(AbstractTaskList list, Task task){
        list.add(task);
    }
}
