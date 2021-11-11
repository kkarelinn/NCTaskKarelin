package ua.edu.sumdu.j2se.karelin.tasks;

import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task> , Cloneable{

    public AbstractTaskList clone () throws CloneNotSupportedException{
        return (AbstractTaskList)super.clone();
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
    public final AbstractTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || from > to || this.size() == 0) {
            throw new IllegalArgumentException("Щось пішло не так");
        }

        AbstractTaskList list = TaskListFactory.createTaskList(getType());

        //Stream API
        assert list != null;
        this.getStream().filter((t) ->
                t.nextTimeAfter(from) <= to && t.nextTimeAfter(from) != -1).forEach(list::add);

        // без Stream API
       /* for (int i = 0; i < size(); i++) {
            Task t = this.getTask(i);
            int timeRun = t.nextTimeAfter(from);      //шукаємо серед задач час виконання
            if ((timeRun <= to) && (timeRun != -1)) {
                list.add(t);
            }
        }*/
        return list;
    }

    public String toString() {
        Iterator<Task> it = iterator();
        if (!it.hasNext())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" [");
        while (it.hasNext()) {
            Task t = it.next();
            sb.append(t.toString());
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
        }
        return sb.toString();
    }


}

