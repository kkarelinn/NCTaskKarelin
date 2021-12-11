package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Set;

public class ViewForRemove implements View {

    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.print("What task would you like to remove from list? Please, enter the number: ");
        return ParseData.getActionFromLine(list.size());
    }

    @Override
    public void printCalendar(Map<LocalDateTime, Set<Task>> map) {
    }

    @Override
    public Task getInfo(Task task) {
        return null;
    }


    @Override
    public int getInfo() {
        return 0;
    }

    @Override
    public boolean getAnswer() {
        System.out.print("Would you like to remove one more task? (y/n): ");
        return ParseData.getBooFromLine();
    }

    @Override
    public void printMessage(String message) {
        System.out.println(message);
    }

}
