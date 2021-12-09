package ua.edu.sumdu.j2se.karelin.tasks;

import ua.edu.sumdu.j2se.karelin.tasks.controller.MainController;
import ua.edu.sumdu.j2se.karelin.tasks.model.*;
import ua.edu.sumdu.j2se.karelin.tasks.view.MainView;

import java.io.*;
import java.time.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        MainController mvc = new MainController(new ArrayTaskList(), new MainView());
        mvc.start();
    }


}

