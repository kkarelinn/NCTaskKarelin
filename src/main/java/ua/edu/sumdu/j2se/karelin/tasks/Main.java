package ua.edu.sumdu.j2se.karelin.tasks;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println("Hello");
//
        Task task1 = new Task("first", 100, true);
        Task cloneTask = task1.clone();
        System.out.println("клоны Задач одинаковые ? : "+task1.equals(cloneTask));
        task1.setTitle("New");
        System.out.println("клоны после изменений в одной задаче одинаковые ? : "+task1.equals(cloneTask));
        System.out.println(task1);
        System.out.println(cloneTask);
        System.out.println();

       AbstractTaskList listArray = new LinkedTaskList();

        Task task2 = new Task("second", 200);
        Task task3 = new Task("third", 300, true);

        listArray.add(task1);
        listArray.add(task2);
        listArray.add(task3);

       AbstractTaskList clone = listArray.clone();
        System.out.println("клоны списков одинаковые ? : "+listArray.equals(clone));

        System.out.println(listArray);

        System.out.println("Изменяем списки и сравниваем, меняются оба или по-отдельности...");
        clone.getTask(0).setTime(200000);
        System.out.println("Clone size before " + clone.size());
        clone.remove(task3);
        System.out.println("Clone size after remove " + clone.size());

        System.out.println("original");
        System.out.println(listArray);
        System.out.println("клон");
        System.out.println(clone);
        System.out.println("-------------------");
        System.out.println("клоны списков ПОСЛЕ ИЗМЕНЕНИЙ одинаковые ? : "+listArray.equals(clone));

        System.out.println(listArray.hashCode());
        System.out.println(clone.hashCode());

        System.out.println();
        System.out.println("....работа со Stream API....");
        System.out.println(listArray.getClass().getSimpleName());

        listArray.getStream().forEach(System.out::println);
        System.out.println(listArray.incoming(150,301));
//        list.stream().forEach(System.out::println);


    }
}
