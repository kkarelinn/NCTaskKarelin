package ua.edu.sumdu.j2se.karelin.tasks;

/**
 * Класс Task реалізує додаток, для роботи з запланованими задачами
 * на певний проміжок часу
 *
 * @author Andrii Karelin
 * @version 1.0
 */
public class Task {

    private String title;
    private int time;           //час виконання одиночної задачі
    private int start;          //початок інтервалу повторюваної задачі
    private int end;            //кінец інтервалу повторюваної задачі
    private int interval;
    private boolean isActive;

    /**
     * Створення нового об'єкту одиночної задачі
     * @param title - назва
     * @param time - момент виконання
     */
    public Task(String title, int time) throws Exception {
        if (title.length() == 0 || time < 0) {
            throw new Exception("Неверное значение аргумента");
        }
        this.title = title;
        this.time = time;

    }

    /**
     * Створення нового об'єкту повторюваної задачі
     * @param title - назва
     * @param start - початок інтервалу
     * @param end - кінець інтервалу
     * @param interval - інтервал
     */
    public Task(String title, int start, int end, int interval) throws Exception {
        if (title.length() == 0 || start < 0 || end < start || interval > (end - start)) {
            throw new Exception("Неверное значение аргумента");
        }
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод отримання назви
     * @return  String - назва задачі
     */
    public String getTitle() {
       return title;
    }

    /**
     * Метод встановлення назви
     * @param   title - встановлює назву задачі типу String, якщо параметр не пустий
     */
    public void setTitle(String title) throws Exception {
        if (title.length() == 0) {
            throw new Exception("Неверное значение аргумента");
        }
        this.title = title;
    }

    /**
     * Метод виявлення активності задачі
     * @return  boolean - "true" - активна, інакше - "false"
     */
    public boolean isActive() {
        return isActive;
    }

    /**
     * Метод активації задачі
     * @param   active (boolean)
     */
    public void setActive(boolean active) {
        this.isActive = active;
    }

    /**
     * Метод зчитування часу виконнання задачі
     * @return  int - час одиночної задачі, або початок інтервалу повторюваної задачі
     */
    public int getTime() {
        return (!isRepeated()) ? time : start;
    }

    /**
     * Метод встановлення часу виконання одиночної задачі
     * та перетворення повторюваної на одиночну
     * @param   time (int) - момент виконання, при умові, що він не відємний
     */
    public void setTime(int time) throws Exception {
        if (time < 0) {
            throw new Exception("Неверное значение аргумента");
        }
        this.time = time;
        if (isRepeated()) {
            this.start = 0;
            this.end = 0;
            this.interval = 0;
        }
    }

    /**
     * Метод зчитування часу початку задачі
     * @return  int - початок інтервалу, або часу задачі
     */
    public int getStartTime() {
        return (isRepeated()) ? start : time;
    }

    /**
     * Метод зчитування часу кінця інтервалу задачі
     * @return  int - кінец інтервалу,  або час задачі
     */
    public int getEndTime() {
        return (isRepeated()) ? end : time;
    }

    /**
     * Метод зчитування інтервалу задачі
     * @return  int - інтервал, або 0 - якщо немає повторів
     */
    public int getRepeatInterval() {
        return (isRepeated()) ? interval : 0;
    }

    /**
     * Метод визначення початку, кінця і інтервалу повторюваної задачі
     * @param start - початок інтервалу
     * @param end - кінець інтервалу
     * @param interval - інтервал
     * встановлює параметри, за умови невідємних величин часу, наявності проміжку, та інтервалу
     */
    public void setTime(int start, int end, int interval) throws Exception {
        if (interval < 0 || start < 0 || start > end || interval > (end - start)) {
            throw new Exception("Неверное значение аргумента");
        }
        if (!isRepeated()) {
            this.time = 0;
        }
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    /**
     * Метод зчитування типу задачі
     * @return  boolean - повертає true, якщо повторювана
     */
    public boolean isRepeated() {
        return (start > 0 && start < end) && ((end - start) / interval) > 1 ;
    }

    /**
     * Метод знаходження наступного моменту виконання задачі після заданого часу
     * @param current - поточний час
     * @return - int - час виконання найближчої задачі
     * якщо задача неактивна, або після заданого часу не виконується, return -1
     */
    public int nextTimeAfter(int current) {
        if (current < 0 || !isActive) {
            return -1;
        }
        if (!isRepeated() && (current < time)) {        //перевірка часу одноразової задачі
            return time;
        }
        if (isRepeated()) {
            if (current < start) {                      //перевірка початку інтервалу
                return start;
            } else {
                int tempCountTimes = (end - start) / interval;  //кількість повторень у проміжку start...end
                for (int i = 1; i < tempCountTimes; i++) {
                    int tempStart = i * interval + start;       //найближча точка повторення
                    if (current < tempStart) return tempStart;
                }
            }
        }
        return -1;
    }
}
