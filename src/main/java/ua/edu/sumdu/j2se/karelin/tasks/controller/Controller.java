package ua.edu.sumdu.j2se.karelin.tasks.controller;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.view.View;

public abstract class Controller {
    static final Logger log = Logger.getLogger(Controller.class);
    public  static  final int MAIN_MENU_ACTION = 0;
    public  static  final int CURRENT_LIST = 1;
    public  static  final int CALENDAR_ACTION = 2;
    public  static  final int ADD_TASK_ACTION = 3;
    public  static  final int REMOVE_TASK_ACTION = 4;
    public  static  final int SET_ALERT_ACTION = 5;
    public  static  final int FINISH_ACTION = 6;
    public  static  final int DETAIL_INFO = 7;

    protected View view;
    protected int actionToPerform = 0;

    public Controller(View view, int actionToPerform) {
        this.view = view;
        this.actionToPerform = actionToPerform;
    }

    public boolean canProcess(int action){
        return action == actionToPerform;
    }

    public int process(AbstractTaskList taskList){  //отрисовка действия и возврат в главное меню
        return view.printInfo(taskList);
    }

}


