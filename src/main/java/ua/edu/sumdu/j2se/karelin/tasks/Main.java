package ua.edu.sumdu.j2se.karelin.tasks;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {

    public static void main(String[] args) throws Exception {
//        System.out.println("Hello");

        // Тестируем дульту времени и интервал (повторяемость)
      /*  LocalDateTime t1 = LocalDateTime.now().plusDays(20);
        LocalDateTime t2 = LocalDateTime.from(t1);
        System.out.println("равные ссылки? "+ t1.isEqual(t2));
        System.out.println(t1);
        System.out.println(t2);
        int interval = 5;
        Duration dur = Duration.between(t1, t2);
        System.out.println("is low: "+ t1.compareTo(t2));
        System.out.println(dur.getSeconds());
        System.out.println(dur.getSeconds()/interval);*/


        /*  Проверка hashCode(), equals(), clone() отдельных ЗАДАЧ*/
        Task task1 = new Task("first", LocalDateTime.now().plusDays(10), true);
//        Task cloneTask = task1.clone();
//        System.out.println( "Равны ли таск1 и clone :" + task1.equals(cloneTask));
//        System.out.println(task1);
//        System.out.println(cloneTask);
//        System.out.println("меняем время первой задачи...");
//        task1.setTime(task1.getTime().plusDays(2));
//        System.out.println( "Равны ли таск1 и clone :" + task1.equals(cloneTask));
//        System.out.println(task1);
//        System.out.println(cloneTask);
//        System.out.println("----------------");
//
//        System.out.println("проверяем двеодинаковые НЕ КЛОН задачи...");
//        Task task1nc = new Task("first", LocalDateTime.now().plusDays(10), true);
//        Task task2nc = new Task("first", LocalDateTime.now().plusDays(10), true);
//        System.out.println( "Равны ли таск1nc и task2nc :" + task1nc.equals(task2nc));
//        System.out.println(task1nc);
//        System.out.println(task2nc);
//        System.out.println("меняем время первой задачи...");
//        task1nc.setTime(task1nc.getTime().plusDays(7));
//        System.out.println( "Равны ли таск1nc и task2nc :" + task1nc.equals(task2nc));
//        System.out.println(task1nc);
//        System.out.println(task2nc);
//        System.out.println(task1nc.hashCode());
//        System.out.println(task2nc.hashCode());

        /*  Проверка hashCode(), equals(), clone() СПИСКОВ*/
        Task task2 = new Task("second", LocalDateTime.now().plusDays(20), LocalDateTime.now().plusDays(30).plusSeconds(10), 5);
        task2.setActive(true);
        //Task task3 = new Task("third", LocalDateTime.now().plusDays(30), true);
        Task task3 = task1.clone();
        task3.setTitle("привет");


        AbstractTaskList listArray = new LinkedTaskList();
        listArray.add(task1);
        listArray.add(task2);
        listArray.add(task3);


      /*  AbstractTaskList clone = listArray.clone();
        System.out.println("клоны списков одинаковые ? : " + listArray.equals(clone));
        System.out.println(listArray);
        System.out.println(clone);
        System.out.println(listArray.hashCode());
        System.out.println(clone.hashCode());
        System.out.println("Clone size before " + clone.size());
        System.out.println("Изменяем списки и сравниваем, меняются оба или по-отдельности...");
       // listArray.getTask(0).setTime(LocalDateTime.now().plusMonths(3));
        clone.remove(task3);
        System.out.println("Clone size after remove " + clone.size());

        System.out.println("original");
        System.out.println(listArray);
        System.out.println("клон");
        System.out.println(clone);
        System.out.println("-------------------");
        System.out.println("клоны списков ПОСЛЕ ИЗМЕНЕНИЙ одинаковые ? : " + listArray.equals(clone));

        System.out.println(listArray.hashCode());
        System.out.println(clone.hashCode());


        System.out.println();
        System.out.println("....работа со Stream API....");
        System.out.println(listArray.getClass().getSimpleName());

        listArray.getStream().forEach(System.out::println);
        System.out.println("-------");*/
      /*  System.out.println("---работа со списком итератор и МАР");
        System.out.println("список предстоящих задач");
        Iterable<Task> iterable = Tasks.incoming(listArray,LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(100));
        AbstractTaskList list = (AbstractTaskList) Tasks.incoming(listArray,LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(100));
        System.out.println(iterable);
    //    System.out.println(list);
        Map<LocalDateTime, Set<Task>> map = Tasks.calendar(listArray,LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(100));*/

       // Main.printMap(map);
        System.out.println("----------Testing Output/Input Streams-----------");
        System.out.println(listArray);
        System.out.println("=============");
        AbstractTaskList newlist = new ArrayTaskList();
//    TaskIO.writeBinary(listArray, new File("Tank.txt"));
   TaskIO.writeText(listArray, new File("Tank.json"));
//        TaskIO.readBinary(newlist, new File("Tank.txt"));
       // System.out.println(newlist);
   TaskIO.readText(newlist, new File("Tank.json"));
        System.out.println();
        System.out.println("newList after import Gson");
        System.out.println(newlist);
        System.out.println("--Читаем из файла--");
    //    System.out.println(newlist);


        /*String ses1 = "doct";
        String ses2="";
        Main.writeBinary(ses1, new File("Tank.txt"));
        Main.readBinary(ses2, new File("Tank.txt"));*/
//        System.out.println(ses1);
//        System.out.println(ses2);

//

    }
    public static void printMap(Map<LocalDateTime, Set<Task>> map){
        for(Map.Entry<LocalDateTime, Set<Task>> entry : map.entrySet()) {
            LocalDateTime date = entry.getKey();
            StringBuilder sb = new StringBuilder(date.toString()).append(": ");
            Set<Task> tasks = entry.getValue();
            for (Task t: tasks) {
                sb.append(t.getTitle()).append(", ");
            }
            System.out.println(sb.substring(0, sb.length()-2));
        }
    }

    public static void writeBinary(String text, File file) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            System.out.println("Начинаем работу врайтера....");
            oos.writeUTF(text);
            } catch (Exception ex) {

            System.out.println(ex.getMessage());
        }
    }
    public static void readBinary(String text, File file) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            System.out.println("Начинаем работу ридера....");
            String s = ois.readUTF();
            System.out.println("-----------");

        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();

        }
    }
}

