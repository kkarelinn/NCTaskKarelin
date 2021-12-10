package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForRemoveTask extends Controller{
    public ControllerForRemoveTask(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int process(AbstractTaskList taskList) {
      return super.process(taskList);
    }

    public static void removeTask(AbstractTaskList list, int index){
        list.remove(list.getTask(index));
    }
    public static int getListSize(AbstractTaskList list){
        return list.size();
    }
}
