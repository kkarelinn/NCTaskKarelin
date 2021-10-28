package ua.edu.sumdu.j2se.karelin.tasks;

public class ArrayTaskList extends AbstractTaskList {

    private Task[] taskMass = new Task[10]; //розмір масиву задач "за замовчуванням".

    public ArrayTaskList(Task[] taskMass) {
        this.taskMass = taskMass;
    }

    public ArrayTaskList() {
        super();
    }

    /**
     * Додавання нової не пустої задачі в масив задач
     *
     * @param task - нова задача
     * @throws Exception - виключення, якщо задачу не передали в метод
     */
    @Override
    public void add(Task task) throws IllegalArgumentException {
        if (task == null) {
            throw new IllegalArgumentException();
        }
        int size = taskMass.length;
        for (int i = 0; i < size; i++) {       //шукаємо вільне місце для нової задачі
            if (taskMass[i] == null) {
                taskMass[i] = task;
                if ((size - i) < 2) {           //якщо скінчилось місце, збільшуємо масив у 2 рази
                    Task[] newMass = new Task[size * 2];
                    System.arraycopy(taskMass, 0, newMass, 0, size);
                    taskMass = newMass.clone();
                }
                break;
            }
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
        int size = taskMass.length;
        for (int i = 0; i < size; i++) {
            if (taskMass[i].getTitle().equals(task.getTitle())) {
                taskMass[i] = null;
                for (int j = i + 1; j < size; j++) {
                    taskMass[j - 1] = taskMass[j];
                }
                taskMass[size - 1] = null;
                return true;
            }
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
        int s = 0;
        for (Task temp : taskMass) {
            if (temp != null) {
                s++;
            }
        }
        return s;
    }

    /**
     * Метод повертає задачу з переданим порядковим номером, якщо він в межах масиву задач
     *
     * @param index - переданий номер задачі
     * @return - Task задача потрібного номеру
     * @throws Exception - номер поза можливим інтервалом
     */
    @Override
    public Task getTask(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index > size() - 1) {
            throw new IndexOutOfBoundsException("невірний номер задачі");
        }
        return taskMass[index];
    }
}

