package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForAddTask extends Controller {
    public ControllerForAddTask(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        while (true) {
            Task t = view.getInfo(null);
            if (t != null) taskList.add(t);
            if (!view.getAnswer()) break;
        }
        return MAIN_MENU_ACTION;
    }


}
