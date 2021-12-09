package ua.edu.sumdu.j2se.karelin.tasks.model;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class Tasks {
    public static Iterable<Task> incoming(Iterable<Task> tasks, LocalDateTime start, LocalDateTime end)
            throws IllegalArgumentException, CloneNotSupportedException {
        if (tasks == null || start == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong arguments");
        }
      //вариант с new ArrayList()
       return StreamSupport.stream(tasks.spliterator(), false).filter((t) -> t.nextTimeAfter(start) != null &&
               (t.nextTimeAfter(start).compareTo(end)) <= 0).collect(Collectors.toList());
       /* return new Iterable<>() {

            @Override
            public String toString() {
                StringBuilder sb = new StringBuilder("TASKs AHEAD (from Iterable<Task>) ");
                Iterator<Task> it = iterator();
                if (!it.hasNext())
                    return sb.append("[]").toString();

                sb.append("[");
                while (it.hasNext()) {
                    sb.append("\n");
                    Task t = it.next();
                    sb.append(t.toString());
                    if (!it.hasNext())
                        return sb.append("\n]").toString();
                    sb.append(", ");
                }
                return sb.toString();
            }

            @Override
            public Iterator<Task> iterator() {
                return StreamSupport.stream(tasks.spliterator(), false).filter((t) -> t.nextTimeAfter(start) != null &&
                        (t.nextTimeAfter(start).compareTo(end)) <= 0).collect(Collectors.toList()).iterator();
            }
        };*/
    }


    public static SortedMap<LocalDateTime, Set<Task>> calendar(Iterable<Task> tasks, LocalDateTime start,
                                                      LocalDateTime end) throws CloneNotSupportedException {
        if (tasks == null || start == null || start.isAfter(end)) {
            throw new IllegalArgumentException("Wrong arguments");
        }
        SortedMap<LocalDateTime, Set<Task>> map = new TreeMap<>();

        /* Фильтруем задачи, выполняющиеся хоть раз в заданный период;
        *   перебираем моменты выполнения и затем заносим в МАР (момент выполнения + задача).
        *   Если map.key (время) уже есть - добавляем к Set(map.value),
        *   если нет - создаем keq/value (момент/задача)
        * */
        incoming(tasks, start, end).forEach(t->{
            LocalDateTime nextTaskDate = t.nextTimeAfter(start);
            while (nextTaskDate != null && nextTaskDate.compareTo(end) <= 0) {
                if ((map.containsKey(nextTaskDate)))  map.get(nextTaskDate).add(t);
                else map.put(nextTaskDate, new HashSet<>(List.of(t)));
                nextTaskDate = t.nextTimeAfter(nextTaskDate);
            }
        });

       return map;
    }


}
