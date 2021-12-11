package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForCurrentTaskList extends Controller {
    public ControllerForCurrentTaskList(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {

        view.printInfo(taskList);
        if (taskList.size() == 0) return MAIN_MENU_ACTION;
        if (view.getAnswer()) {
            return DETAIL_INFO;
        }
        return MAIN_MENU_ACTION;
    }


}
