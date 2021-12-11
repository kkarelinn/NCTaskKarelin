package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.notificator.Notificator;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public class ControllerForAllert extends Controller {

    public ControllerForAllert(View view, int actionToPerform) {
        super(view, actionToPerform);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        int time = view.getInfo();
        notifyLaunch(taskList, time);
        return super.action(taskList);
    }


    public static void notifyLaunch(AbstractTaskList list, int time) {
        Notificator notificator = new Notificator(list);
        notificator.setTimeInMinutes(time);
        notificator.setDaemon(true);
        notificator.start();
        list.notifyChange(" notificator was added with time " + time + "min");
    }
}
