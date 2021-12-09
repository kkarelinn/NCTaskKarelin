package ua.edu.sumdu.j2se.karelin.tasks.model;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * Класс Task реалізує додаток, для роботи з запланованими задачами
 * на певний проміжок часу
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class Task implements Cloneable, Serializable {

    private String title;
    private LocalDateTime time;           //час виконання одиночної задачі
    private LocalDateTime start;          //початок інтервалу повторюваної задачі
    private LocalDateTime end;            //кінец інтервалу повторюваної задачі
    private int interval;
    private boolean isActive;

    public Task(){}
    /**
     * Створення нового об'єкту одиночної задачі
     *
     * @param title - назва
     * @param time  - момент виконання
     */
    public Task(String title, LocalDateTime time) throws IllegalArgumentException {
        if (title.length() == 0 || time == null) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.title = title;
        this.time = time;
    }

    public Task(String title, LocalDateTime time, boolean acivate) throws IllegalArgumentException {
        if (title.length() == 0 || time == null) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.title = title;
        this.time = time;
        this.isActive = acivate;
    }

    /**
     * Створення нового об'єкту повторюваної задачі
     *
     * @param title    - назва
     * @param start    - початок інтервалу
     * @param end      - кінець інтервалу
     * @param interval - інтервал
     */
    public Task(String title, LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (title.length() == 0 || start == null || interval <= 0 || start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод отримання назви
     *
     * @return String - назва задачі
     */
    public String getTitle() {
        return title;
    }

    /**
     * Метод встановлення назви
     *
     * @param title - встановлює назву задачі типу String, якщо параметр не пустий
     */
    public void setTitle(String title) throws Exception {
        if (title.length() == 0) {
            throw new Exception("Wrong argument");
        }
        this.title = title;

    }

    /**
     * Метод виявлення активності задачі
     *
     * @return boolean - "true" - активна, інакше - "false"
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Метод активації задачі
     *
     * @param active (boolean)
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * Метод зчитування часу виконнання задачі
     *
     * @return int - час одиночної задачі, або початок інтервалу повторюваної задачі
     */
    public LocalDateTime getTime() {
        return (!isRepeated()) ? time : start;
    }

    /**
     * Метод встановлення часу виконання одиночної задачі
     * та перетворення повторюваної на одиночну
     *
     * @param time (int) - момент виконання, при умові, що він не відємний
     */
    public void setTime(LocalDateTime time) throws IllegalArgumentException {
        if (time == null) {
            throw new IllegalArgumentException("Wrong argument");
        }
        this.start = null;
        this.end = null;
        this.interval = 0;
        this.time = time;

    }

    /**
     * Метод зчитування часу початку задачі
     *
     * @return int - початок інтервалу, або часу задачі
     */
    public LocalDateTime getStartTime() {
        return (isRepeated()) ? start : time;
    }

    /**
     * Метод зчитування часу кінця інтервалу задачі
     *
     * @return int - кінец інтервалу,  або час задачі
     */
    public LocalDateTime getEndTime() {
        return (isRepeated()) ? end : time;
    }

    /**
     * Метод визначення початку, кінця і інтервалу повторюваної задачі
     *
     * @param start    - початок інтервалу
     * @param end      - кінець інтервалу
     * @param interval - інтервал
     *                 встановлює параметри, за умови невідємних величин часу, наявності проміжку, та інтервалу
     */
    public void setTime(LocalDateTime start, LocalDateTime end, int interval) throws IllegalArgumentException {
        if (interval < 0 || start == null || end.isBefore(start)) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        this.time = null;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод зчитування інтервалу задачі
     *
     * @return int - інтервал, або 0 - якщо немає повторів
     */
    public int getRepeatInterval() {
        return interval;
    }

    /**
     * Метод зчитування типу задачі (повторювана/не повторювана)
     *
     * @return boolean - повертає true, якщо повторювана
     */
    public boolean isRepeated() {
        return (start != null && end != null && (start.compareTo(end) <= 0)
                && ((Duration.between(start, end).getSeconds()) ) > 0);         //zero
    }

    /**
     * Метод знаходження наступного моменту(часу) виконання задачі після заданого часу
     *
     * @param current - поточний час
     * @return - int - час виконання найближчої задачі
     * якщо задача неактивна, або після заданого часу не виконується, return -1
     */
    public LocalDateTime nextTimeAfter(LocalDateTime current) {
        if (current == null || !isActive) {
            return null;
        }
        if (!isRepeated() && (current.isBefore(time))) {        //перевірка часу одноразової задачі
            return time;
        }
        if (isRepeated()) {
            LocalDateTime tempStart = start;
            while (tempStart.compareTo(end) <= 0) {             //перевірка моменту кожного кроку від start
                if (tempStart.isAfter(current)) return tempStart;
                tempStart = tempStart.plusSeconds(interval);
            }
        }
        return null;
    }

    @Override
    public Task clone() throws CloneNotSupportedException {
        return (Task) super.clone();
    }

    @Override
    public String toString() {
        return "{" +
                "\"" + title + "\"" +
                ", time: " + ((time != null) ? time.toLocalDate()+" "+time.toLocalTime().truncatedTo(ChronoUnit.SECONDS) : "-") +
                ", start_T: " + ((start != null) ? start.toLocalDate()+" "+start.toLocalTime().truncatedTo(ChronoUnit.SECONDS) : "-") +
                ", end_T: " + ((end != null) ? end.toLocalDate()+" "+end.toLocalTime().truncatedTo(ChronoUnit.SECONDS) : "-") +
                ", int(s): " + ((interval != 0) ? interval : "-") +
                ((isActive) ? ", ACTIVE," : ", no active,") + " isRep :" + isRepeated() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        // isActive - не учитываем -
        return (Objects.equals(time, task.time) && Objects.equals(start, task.start) && Objects.equals(end, task.end)
                && interval == task.interval && title.equals(task.title) && isActive == task.isActive);
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (start != null ? start.hashCode() : 0);
        result = 31 * result + (end != null ? end.hashCode() : 0);
        result = 31 * result + interval;
        result = 31 * result + (isActive ? 1 : 0);
        return result;

    }


}
