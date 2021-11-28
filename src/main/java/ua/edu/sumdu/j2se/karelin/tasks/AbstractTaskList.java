package ua.edu.sumdu.j2se.karelin.tasks;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Objects;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {

    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }

    abstract void add(Task task) throws IllegalArgumentException;

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public abstract Stream<Task> getStream();

    abstract ListTypes.types getType();

    /**
     * Метод для створення списку запланованих до виконанння задач в заданий проміжок часу
     *
     * @param from - початок інтервалу
     * @param to   - кінець інтервалу
     * @return - обьєкт ArrayTaskList зі масивом запланованих задач
     */
   /* public final AbstractTaskList incoming(LocalDateTime from, LocalDateTime to) throws IllegalArgumentException {
        if (from == null || from.isAfter(to) || this.size() == 0) {
            throw new IllegalArgumentException("Wrong arguments");
        }

        AbstractTaskList list = TaskListFactory.createTaskList(getType());

        //Stream API
        this.getStream().filter((t) -> t.nextTimeAfter(from) != null &&
                (t.nextTimeAfter(from).compareTo(to)) <= 0).forEach(Objects.requireNonNull(list)::add);
        // без Stream API
        for (int i = 0; i < size(); i++) {
            Task t = this.getTask(i);
            LocalDateTime timeRun = t.nextTimeAfter(from);      //шукаємо серед задач час виконання
            if ((timeRun != null) && (timeRun.isBefore(to))) {
                list.add(t);
            }
        }
        return list;
    }*/

    public String toString() {
        Iterator<Task> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" [");
        while (it.hasNext()) {
            sb.append("\n");
            Task t = it.next();
            sb.append(t.toString());
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
        }
        return sb.toString();
    }


}

