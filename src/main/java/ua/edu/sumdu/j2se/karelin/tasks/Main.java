package ua.edu.sumdu.j2se.karelin.tasks;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("Hello");

        AbstractTaskList list = new LinkedTaskList();
        list.add(new Task("task1", 10));
        list.add(new Task("task2", 20));
        list.add(new Task("task3", 30));
        list.add(new Task("task3", 40));
        list.add(new Task("task5", 50));
        list.add(new Task("task6", 60));
        list.add(new Task("task7", 70));
        list.add(new Task("task8", 80));
        list.add(new Task("task9", 90));
        list.add(new Task("task10", 100));
        list.add(new Task("task11", 110));
        list.add(new Task("task12", 120));

        System.out.println(list);
       // ListTipes.types
        for (ListTypes.types t:ListTypes.types.values()
             ) {
            System.out.println(t.toString());

        }
        System.out.println(ListTypes.types.values());


        AbstractTaskList list1 = list.incoming( 29, 81);
        System.out.println(list1);

    }
}
