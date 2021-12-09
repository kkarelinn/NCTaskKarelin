package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.controller.ControllerForAllert;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.util.ParseData;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ViewForAllert implements View {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList list) {

        System.out.print("Please, enter notification time in minutes: ");
        ControllerForAllert.notifyLaunch(list, ParseData.getIntFromLine());
        return Controller.MAIN_MENU_ACTION;

    }
}

