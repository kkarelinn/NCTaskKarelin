package ua.edu.sumdu.j2se.karelin.tasks.controller;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.controller.observer.ObserverForChange;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.TaskIO;
import ua.edu.sumdu.j2se.karelin.tasks.view.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController extends Controller {

    private AbstractTaskList taskList;

    private final List<Controller> controllers = new ArrayList<Controller>();

    public MainController(AbstractTaskList abstractTaskList, View mainView) {
        super(mainView, MAIN_MENU_ACTION);   //Controller.MAIN_MENU_ACTION - цифра int пункта меню
        this.taskList = abstractTaskList;

        log.info("\n\nProgram was starting");


        //создаем список контроллеров и задаем каждому действие, которое он может делать
        controllers.add(this);
        controllers.add(new ControllerForCurrentTaskList(new ViewForCurrentTaskList(), CURRENT_LIST));
        controllers.add(new ControllerForCalendar(new ViewForCalendar(), CALENDAR_ACTION));
        controllers.add(new ControllerForAddTask(new ViewForAddTask(), ADD_TASK_ACTION));
        controllers.add(new ControllerForRemoveTask(new ViewForRemove(), REMOVE_TASK_ACTION));
        controllers.add(new ControllerForAllert(new ViewForAllert(), SET_ALERT_ACTION));
        controllers.add(new ControllerForDetail(new ViewForDetail(), DETAIL_INFO));
        controllers.add(new ControllerForChange(new ViewForChange(), CHANGE_ACTION));


    }

    public MainController() {
        super(new MainView(), MAIN_MENU_ACTION);   //Controller.MAIN_MENU_ACTION - цифра int пункта меню
        this.taskList = new ArrayTaskList();
    }

    public void start() {
        TaskIO.readBinary(taskList, new File("tasks.bin"));
        new ObserverForChange(taskList, new ViewObserverMessage());
        log.info("Adding task observer - OK");

        action(taskList);
    }

    @Override
    public int action(AbstractTaskList taskList) {
        int action = view.printInfo(taskList); //отрисовка главного меню и возврат действия пользователя
        for (; ; ) {
            for (Controller controller : controllers) {
                if (controller.canProcess(action)) {             //если выбор действи совпадает с цифрой контроллера
                    action = controller.action(this.taskList); // делаем действие
                }
            }
            if (action == FINISH_ACTION) {
                log.info("Shut down the program\n");

                System.exit(0);
            }
        }
    }
}
