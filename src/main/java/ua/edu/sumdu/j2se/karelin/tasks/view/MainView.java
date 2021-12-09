package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainView implements View{
    boolean start = true;
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    @Override
    public int printInfo(AbstractTaskList list) {

        System.out.println();
        if (start){ System.out.println("-----It's your \"TASK'S CALENDAR\"----\n");
        start = false;}

        if (list.size() > 0) {
            System.out.println("You have SOME TASKS in you calendar. What would you like to do?");
        } else {
            System.out.println("You have NO ONE TASKS in you calendar. What would you like to do?");
        }
        System.out.println("1. View ALL tasks from your tasks list");
        System.out.println("2. View a list of scheduled tasks until the end of the CURRENT week");
        System.out.println("3. Add NEW task to your tasks list");
        System.out.println("4. REMOVE some task from your tasks list");
        System.out.println("5. Set an ALERT time before running scheduled tasks");
        System.out.println("6. EXIT program\n");
        System.out.print("Please enter your choice (number): ");
        int choose = 0;
        while(true) {
            try {
                choose = Integer.parseInt(br.readLine());
                if (choose > 0 && choose < 7){
                    return choose;
                }
                System.out.print("Please enter VALID choice (number): ");

            } catch (IOException e) {
                e.printStackTrace();
            }
            catch (Exception e)
            { System.out.print("Please enter VALID choice (number): ");}

        }
    }
}
