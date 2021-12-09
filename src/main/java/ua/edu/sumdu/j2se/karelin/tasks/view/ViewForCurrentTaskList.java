package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.ParseData;

public class ViewForCurrentTaskList implements View{

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.println();
        System.out.println("Your current task list is looks like this:");
        System.out.println(list);
        System.out.println();
        System.out.print("Would you like to make some change in some task? (y/n): ");
        boolean action = ParseData.getBooFromLine();
        if (action){ return Controller.DETAIL_INFO;}
        return Controller.MAIN_MENU_ACTION;
    }
}
