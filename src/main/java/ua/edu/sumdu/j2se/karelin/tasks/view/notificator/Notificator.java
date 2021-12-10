package ua.edu.sumdu.j2se.karelin.tasks.view.notificator;

import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class Notificator extends Thread {

    private AbstractTaskList list;
    private int timeInMinutes = -1;

    public void setTimeInMinutes(int timeInMinutes) {
        this.timeInMinutes = timeInMinutes;
    }

    public Notificator(AbstractTaskList list) {
        this.list = list;
    }

    @Override
    public void run() {
        LocalDateTime curTime;
        System.out.println();

        while (true) {
            curTime = LocalDateTime.now();

            for (Task t : list) {
                LocalDateTime next = t.nextTimeAfter(LocalDateTime.now());
                if (next != null){
                    int deltaTime = (int) (next.toEpochSecond(ZoneOffset.UTC) - curTime.toEpochSecond(ZoneOffset.UTC)) / 60;
                    if (next.isAfter(curTime) && deltaTime < timeInMinutes) {
                        System.out.println("\nAttention!");
                        System.out.println(deltaTime + " minutes left before the start of the task " + t.getTitle());
                    }
                }
            }
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

