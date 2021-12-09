package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.controller.ControllerForRemoveTask;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.ParseData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ViewForRemove implements View{
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("What task would you like to remove from list? Please, enter the number: ");
        int listIndex = ParseData.getListIdFromLine(list);
        ControllerForRemoveTask.removeTask(list, listIndex);
        System.out.print("Would you like to remove one more task? (y/n): ");
        if (ParseData.getBooFromLine()) {
            printInfo(list);
        }
        return Controller.MAIN_MENU_ACTION;

    }
}
