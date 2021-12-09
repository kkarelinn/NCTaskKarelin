package ua.edu.sumdu.j2se.karelin.tasks.model;

import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.view.observer.ObserverForChange;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;

public abstract class AbstractTaskList implements Iterable<Task>,
        Cloneable, Serializable {

    static final Logger log = Logger.getLogger(AbstractTaskList.class);
    public AbstractTaskList clone() throws CloneNotSupportedException {
        return (AbstractTaskList) super.clone();
    }

    abstract public void add(Task task) throws IllegalArgumentException;

    public abstract boolean remove(Task task);

    public abstract int size();

    public abstract Task getTask(int index) throws IndexOutOfBoundsException;

    public abstract Stream<Task> getStream();

    abstract ListTypes.types getType();

    public String toString() {
        Iterator<Task> it = iterator();
        if (!it.hasNext())
            return "[]";
        int index = 1;
        StringBuilder sb = new StringBuilder();
        sb.append(this.getClass().getSimpleName()).append(" [");
        while (it.hasNext()) {
            sb.append("\n").append("Task #" + index + ". ");
            Task t = it.next();
            sb.append(t.toString());
            if (!it.hasNext())
                return sb.append(']').toString();
            sb.append(", ");
            index++;
        }
        return sb.toString();
    }

    private ObserverForChange observer=null;

    public void setObserver(ObserverForChange observer) {
        this.observer = observer;
    }

    public void notifyChange(String message) {
        if (observer != null)
        observer.update(message);
    }

}

