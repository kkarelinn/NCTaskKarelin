package ua.edu.sumdu.j2se.karelin.tasks;

import java.io.Serializable;
import java.util.Iterator;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class LinkedTaskList extends AbstractTaskList implements Iterable<Task>, Cloneable, Serializable {
    private int sizeList;
    private Node headNode;
    private Node finalNode;

    @Override
    public LinkedTaskList clone() throws CloneNotSupportedException {
        LinkedTaskList listClone = (LinkedTaskList) super.clone();
        Iterator<Task> origIt = this.iterator();
        Iterator<Task> cloneIt = listClone.iterator();

        while (cloneIt.hasNext()) {
            cloneIt.next();
            cloneIt.remove();
        }
        while (origIt.hasNext()) {
            listClone.add(origIt.next().clone());
        }
        return listClone;
    }

    public LinkedTaskList() {
    }

    /*
     * конструктор для створення LinkedTaskList із масива задач
     */
    public LinkedTaskList(Task[] tasks) {
        this();
        for (Task task : tasks) {
            this.add(task);
        }
    }

    /**
     * Додавання нової не пустої задачі в масив задач
     *
     * @param task - нова задача
     * @throws IllegalArgumentException - виключення, якщо задачу не передали в метод
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        if (headNode == null && finalNode == null) {    //додаємо перший Нод у список
            headNode = new Node(task, null, null);
            finalNode = headNode;
            sizeList++;
        } else {
            Node tempNode = new Node(task, finalNode, null);
            finalNode.setNext(tempNode);
            finalNode = tempNode;
            sizeList++;
        }
    }

    /**
     * Метод видаляє з масиву передану задачу
     *
     * @param task - передана задача для видалення
     * @return - true (успішне видалення), інакше false.
     */
    @Override
    public boolean remove(Task task) {

        Node rmv = searchNode(task);    //шукаємо потрібний Нод
        if (rmv == null) return false;
        if (rmv.isOnlyOneNode()) {       //якщо це єдиний Нод у списку
            headNode = null;
            finalNode = null;
            sizeList--;
            return true;
        }
        if (rmv.isFirstNode()) {     //якщо Нод перший в списку
            headNode = rmv.getNext();
            headNode.setPrev(null);
            sizeList--;
            return true;
        }
        if (rmv.isLastNode()) {     //якщо Нод останній в списку
            finalNode = rmv.getPrev();
            finalNode.setNext(null);
            sizeList--;
            return true;
        }
        if (rmv.isMiddleNode()) {
            Node prev = rmv.getPrev();
            Node next = rmv.getNext();
            prev.setNext(next);
            next.setPrev(prev);
            sizeList--;
            return true;
        }
        return false;
    }

    /**
     * Метод повертає кількість задач із масиву задач
     *
     * @return - int
     */
    @Override
    public int size() {
        return sizeList;
    }

    /**
     * Метод повертає задачу з переданим порядковим номером, якщо він в межах масиву задач
     *
     * @param index - переданий номер задачі
     * @return - Task задача потрібного номеру
     * @throws IndexOutOfBoundsException - номер поза можливим інтервалом
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("невірний номер задачі");
        }
        Node currentNode = headNode;
        int step = 0;
        while (step != index) {
            currentNode = currentNode.getNext();
            step++;
        }
        return currentNode.getTask();
    }

    @Override
    ListTypes.types getType() {
        return ListTypes.types.LINKED;
    }


    @Override
    public Iterator<Task> iterator() {

        return new Iterator<>() {
            private Node currNode = headNode;   //индекс элемента для next()
            private Node lastNode = null;    //Индекс элемента, предыдущего next().

            @Override
            public boolean hasNext() {
                return currNode != null;
            }

            @Override
            public Task next() {
                lastNode = currNode;
                currNode = currNode.getNext();
                return lastNode.getTask();
            }

            @Override
            public void remove() {
                if (lastNode == null) throw new IllegalStateException("Не был вызван метод next()");
                LinkedTaskList.this.remove(lastNode.task);
                lastNode = null;    //--
            }
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LinkedTaskList tasks = (LinkedTaskList) o;
        int length = tasks.sizeList;
        if (sizeList != length) return false;

        Iterator<Task> itCurr = iterator();
        Iterator<Task> itTasks = tasks.iterator();

        while (itCurr.hasNext()) {
            if (!itCurr.next().equals(itTasks.next())) return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        if (headNode == null) return 0;
        int result = 1;
        for (Task t : this) {
            result = 31 * result + (t == null ? 0 : t.hashCode());
        }
        return result;

    }

    /*
     * ДОдаткові функції для LinkedTaskList
     */
    private Node searchNode(Task task) {         //пошук Ноду за назвою задачі
        Node currentNode = headNode;
        while (currentNode != null) {
            if (currentNode.getTask().equals(task)) {
                return currentNode;
            }
            currentNode = currentNode.getNext();
        }
        return null;
    }

    @Override
    public Stream<Task> getStream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    private static class Node {
        private Task task;
        private Node prev;
        private Node next;

        public Node(Task task, Node prev, Node next) {
            if (task == null) {
                throw new IllegalArgumentException();
            }
            this.task = task;
            this.prev = prev;
            this.next = next;
        }

        public Task getTask() {
            return task;
        }

        public void setTask(Task task) {
            if (task != null)
                this.task = task;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getPrev() {
            return prev;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }

        public boolean hasNextNode() {
            return (this.next != null);
        }

        public boolean hasPrevNode() {
            return (this.prev != null);
        }

        public boolean isLastNode() {
            return (!this.hasNextNode() && this.hasPrevNode());
        }

        public boolean isFirstNode() {
            return (this.hasNextNode() && !this.hasPrevNode());
        }

        public boolean isMiddleNode() {
            return (this.hasNextNode() && this.hasPrevNode());
        }

        public boolean isOnlyOneNode() {
            return (!this.hasNextNode() && !this.hasPrevNode());
        }


    }


}
