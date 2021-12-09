package ua.edu.sumdu.j2se.karelin.tasks.view.observer;

import ua.edu.sumdu.j2se.karelin.tasks.controller.MainController;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;

public class ObserverForChange {
    private AbstractTaskList list;

    public ObserverForChange(AbstractTaskList list) {
        this.list = list;
        this.list.setObserver(this);
    }

    public void update(String message) {
        System.out.println(message);
        MainController.writeToFile(list);
    }

}
