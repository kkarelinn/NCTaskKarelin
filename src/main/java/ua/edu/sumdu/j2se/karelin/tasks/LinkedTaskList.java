package ua.edu.sumdu.j2se.karelin.tasks;

public class LinkedTaskList {
    private int sizeList;
    private Node headNode;
    private Node finalNode;

    public LinkedTaskList() {
        super();
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
     * @throws Exception - виключення, якщо задачу не передали в метод
     */
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        if (headNode == null && finalNode == null) {    //додаємо перший Нод у список
            // System.out.println(node);
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
    public boolean remove(Task task) {

        Node rmv = searchNode(task);    //шукаємо потрібний Нод
        if (rmv == null) return false;
        //  System.out.println("deleting " + rmv.getTask().getTitle());

        if (rmv.isOnlyOneNode()) {       //якщо це єдиний Нод у списку
            headNode = null;
            finalNode = null;
            sizeList--;
            return true;
        }
        if (rmv.isFirstNode()) {     //якщо Нод перший в списку
            headNode = rmv.getNext();
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
    public int size() {
        return sizeList;
    }

    /**
     * Метод повертає задачу з переданим порядковим номером, якщо він в межах масиву задач
     *
     * @param index - переданий номер задачі
     * @return - Task задача потрібного номеру
     * @throws Exception - номер поза можливим інтервалом
     */
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

    /**
     * Метод для створення списку запланованих до виконанння зада в заданий проміжок часу
     *
     * @param from - початок інтервалу
     * @param to   - кінець інтервалу
     * @return - обьєкт ArrayTaskList зі масивом запланованих задач
     */
    public LinkedTaskList incoming(int from, int to) throws IllegalArgumentException {
        if (from < 0 || from > to || headNode == null) {
            throw new IllegalArgumentException("Щось пішло не так");
        }
        LinkedTaskList list = new LinkedTaskList();
        Node currentNode = headNode;
        while (currentNode != null) {       //перевіряємо задачу кожного Ноду на відповіднісь часу
            int timeRun = currentNode.getTask().nextTimeAfter(from);
            if ((timeRun <= to) && (timeRun != -1)) {
                list.add(currentNode.getTask());        //додаємо у список
            }
            currentNode = currentNode.getNext();
        }
        return list;
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

    /*
     * ДОдаткові функції для LinkedTaskList
     */
    public Node searchNode(Task task) {         //пошук Ноду за назвою задачі
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
    public String toString() {
        StringBuilder sb = new StringBuilder(" ");
        Node currentNode = headNode;
        while (currentNode != null) {
            sb.append(currentNode.getTask().getTitle() + " ");
            currentNode = currentNode.getNext();
        }
        return (headNode == null) ?
                ("LinkedTaskList{" + sb + "size=" + sizeList + '}' + "\n")
                :
                ("LinkedTaskList{" + sb + "size=" + sizeList + ", headNode=" + headNode.getTask().getTitle()
                        + " " + ", finalNode=" + finalNode.getTask().getTitle() + " " + '}' + "\n");
    }
}
