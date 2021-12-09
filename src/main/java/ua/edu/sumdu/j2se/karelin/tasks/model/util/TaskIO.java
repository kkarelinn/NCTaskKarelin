package ua.edu.sumdu.j2se.karelin.tasks.model.util;

import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.log4j.Logger;
import ua.edu.sumdu.j2se.karelin.tasks.model.AbstractTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.ArrayTaskList;
import ua.edu.sumdu.j2se.karelin.tasks.model.Task;


import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Arrays;


public class TaskIO {
    private static final Logger log = Logger.getLogger(TaskIO.class);

    //сериализуем в поток
    public static void write(AbstractTaskList tasks, OutputStream out) {
        if (tasks == null || out == null) {
            log.error("Mistake with parameters in write method");
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(out)) {

            oos.writeInt(tasks.size());
            for (Task task : tasks) {
                oos.writeInt(task.getTitle().length());
                oos.writeObject(task.getTitle());
                oos.writeBoolean(task.isActive());
                oos.writeInt(task.getRepeatInterval());
                if (task.isRepeated()) {
                    oos.writeObject(task.getStartTime());
                    oos.writeObject(task.getEndTime());
                } else {
                    oos.writeObject(task.getTime());
                }
                oos.flush();
                log.error("writing data to file - OK");
                //   System.out.println("wrote "+ task.getTitle());
            }
        } catch (IOException e) {
            log.error("Error to write file");
            System.out.println(e.getMessage());
        }
    }

    //читаем из потока
    public static void read(AbstractTaskList tasks, InputStream in) {
        if (tasks == null || in == null) {
            log.error("Mistake with parameters in read method");
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (ObjectInputStream ois = new ObjectInputStream(in)) {

            int tasksSize = ois.readInt();
            Task t;
            for (int i = 0; i < tasksSize; i++) {
                ois.readInt();
                String title = (String) ois.readObject();
                boolean isActive = (ois.readBoolean());
                int interval = ois.readInt();
                if (interval > 0) {
                    LocalDateTime start = (LocalDateTime) ois.readObject();
                    LocalDateTime end = (LocalDateTime) ois.readObject();
                    t = new Task(title, start, end, interval);
                    t.setActive(isActive);
                    tasks.add(t);
                } else {
                    LocalDateTime time = (LocalDateTime) ois.readObject();
                    t = new Task(title, time, isActive);
                    tasks.add(t);
                }
            }
            log.info("reading file - OK");
        } catch (IOException | ClassNotFoundException e) {
            log.error("Mistake in file reading");
            System.out.println(e.getMessage());
        }
    }

    //сериализуем в файл
    public static void writeBinary(AbstractTaskList tasks, File file) {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (FileOutputStream fos = new FileOutputStream(file)) {
            write(tasks, fos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }


    }

    //читаем из файла
    public static void readBinary(AbstractTaskList tasks, File file) {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (FileInputStream fis = new FileInputStream(file)) {
            read(tasks, fis);
        } catch (IOException e) {
            System.out.println("You have some problems with base file..");
            System.out.println(e.getMessage());

        }

    }

    //пишем JSon в поток (Gson)
    public static void write(AbstractTaskList tasks, Writer out) {
        if (tasks == null || out == null) {
            log.error("Mistake with parameters in write method");
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();

        Task[] taskMass = tasks.getStream().toArray(Task[]::new);

        gson.toJson(taskMass, out);
        log.info("writing to json - OK");

    }

    //читаем JSon из потока (Gson)
    public static void read(AbstractTaskList tasks, Reader in) {
        if (tasks == null || in == null) {
            log.error("Mistake with parameters in read method");
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateAdapter())
                .create();

        Task[] mass = gson.fromJson(in, Task[].class);
        Arrays.stream(mass).forEach(tasks::add);
        log.info("reading from json - OK");

    }

    //пишем JSon в файл (Gson)
    public static void writeText(AbstractTaskList tasks, File file) {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (FileWriter fw = new FileWriter(file)) {
            write(tasks, fw);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    //читаем JSon из файла (Gson)
    public static void readText(AbstractTaskList tasks, File file) {
        if (tasks == null || file == null) {
            throw new IllegalArgumentException("Something wrong with arguments");
        }
        try (FileReader fr = new FileReader(file)) {
            read(tasks, fr);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //Адаптер Gson for LocalDateTime для массива
    static class LocalDateAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(final JsonWriter jsonWriter, final LocalDateTime localDT) throws IOException {
            if (localDT == null) {
                jsonWriter.nullValue();
            } else {
                jsonWriter.value(localDT.toString());
            }
        }

        @Override
        public LocalDateTime read(final JsonReader jsonReader) throws IOException {
            if (jsonReader.peek() == JsonToken.NULL) {
                jsonReader.nextNull();
                return null;
            } else {
                return LocalDateTime.parse(jsonReader.nextString());
            }
        }
    }

    //-----------

    //Адаптер Gson для чтения кастом класса (как вариант. Не исползуется)
    static class ListAdapter extends TypeAdapter<AbstractTaskList> {

        @Override
        public void write(JsonWriter jsonWriter, AbstractTaskList tasks) throws IOException {

            jsonWriter.beginObject();
            jsonWriter.name(tasks.getClass().getSimpleName());
            jsonWriter.beginArray();
            for (Task t : tasks) {
                jsonWriter.beginObject();
                jsonWriter.name("title").value(t.getTitle());

                if (t.isRepeated()) {
                    jsonWriter.name("time").value("");
                    jsonWriter.name("start_T").value(t.getStartTime().toString());
                    jsonWriter.name("end_T").value(t.getEndTime().toString());
                    jsonWriter.name("int(s)").value(t.getRepeatInterval());
                } else {
                    jsonWriter.name("time").value(t.getTime().toString());
                    jsonWriter.name("start_T").value("");
                    jsonWriter.name("end_T").value("");
                    jsonWriter.name("int(s)").value(0);
                }
                jsonWriter.name("isAct").value(t.isActive());
                jsonWriter.endObject();
            }
            jsonWriter.endArray();
            jsonWriter.endObject();
        }

        @Override
        public AbstractTaskList read(JsonReader jsonReader) throws IOException {
            AbstractTaskList resultList = new ArrayTaskList();

            jsonReader.beginObject();
            jsonReader.nextName();
            jsonReader.beginArray();

            while (jsonReader.hasNext()) {
                Task task;
                jsonReader.beginObject();
                jsonReader.skipValue();
                String title = jsonReader.nextString();
                jsonReader.skipValue();
                String timeS = jsonReader.nextString();
                jsonReader.skipValue();
                String startS = jsonReader.nextString();
                jsonReader.skipValue();
                String endS = jsonReader.nextString();
                jsonReader.skipValue();
                int interval = Integer.parseInt(jsonReader.nextString());
                jsonReader.skipValue();
                boolean isActive = jsonReader.nextBoolean();

                if (startS.isEmpty() && endS.isEmpty()) {
                    task = new Task(title, LocalDateTime.parse(timeS));
                    task.setActive(isActive);
                } else {
                    task = new Task(title, LocalDateTime.parse(startS), LocalDateTime.parse(endS), interval);
                    task.setActive(isActive);
                }

                resultList.add(task);

                jsonReader.endObject();

            }

            jsonReader.endArray();
            jsonReader.endObject();
            return resultList;
        }
    }


    //Адаптер Gson для LocalDateTime для массива (как вариант. Не исползуется)
    static class LocalDateTimeAdapter implements JsonSerializer<LocalDateTime>, JsonDeserializer<LocalDateTime> {

        public JsonElement serialize(LocalDateTime date, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(date.toString());
        }

        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString());
        }
    }


}
