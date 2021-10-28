package ua.edu.sumdu.j2se.karelin.tasks;

public abstract class AbstractTaskList {
    abstract void add(Task task) throws IllegalArgumentException;

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index) throws IndexOutOfBoundsException;

    /**
     * Метод для створення списку запланованих до виконанння задач в заданий проміжок часу
     *
     * @param from - початок інтервалу
     * @param to   - кінець інтервалу
     * @return - обьєкт ArrayTaskList зі масивом запланованих задач
     */
    public AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || from > to || this.size() == 0) {
            throw new IllegalArgumentException("Щось пішло не так");
        }

        //визначаємо тип списку, та встановлюємо відповідний тип ENUM
        ListTypes.types type = (this instanceof ArrayTaskList) ? ListTypes.types.ARRAY : ListTypes.types.LINKED;

        AbstractTaskList list = TaskListFactory.createTaskList(type);
        for (int i = 0; i < size(); i++) {
            Task t = this.getTask(i);
            if (t == null) break;
            int timeRun = t.nextTimeAfter(from);      //шукаємо серед задач час виконання
            if ((timeRun <= to) && (timeRun != -1)) {
                list.add(t);
            }
        }
        return list;
    }
}

