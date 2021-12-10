package ua.edu.sumdu.j2se.karelin.tasks.view;

import ua.edu.sumdu.j2se.karelin.tasks.controller.Controller;
import ua.edu.sumdu.j2se.karelin.tasks.controller.ControllerForCalendar;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

public class ViewForCalendar implements View{
    @Override
    public int printInfo(AbstractTaskList list) {
        System.out.println("Calendar to the end of the CURRENT week");

        try {
            SortedMap map = ControllerForCalendar.getCalendar(list,LocalDateTime.now()
                    , LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).with(LocalTime.MIN));
            printMap(map);
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return Controller.MAIN_MENU_ACTION;
    }
    public void printMap(Map<LocalDateTime, Set<Task>> map){
        if(map.size() == 0){
            System.out.println("the Calendar is empty");
        }
        for(Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            LocalDateTime date = entry.getKey();
            StringBuilder sb = new StringBuilder(new StringBuilder(date.toLocalDate()+" "+date.toLocalTime().truncatedTo(ChronoUnit.SECONDS)).append(" - "));
            Set<Task> tasks = entry.getValue();
            for (Task t: tasks) {
                sb.append(t.getTitle()).append(", ");
            }
            System.out.println(sb.substring(0, sb.length()-2));
        }
    }
}


