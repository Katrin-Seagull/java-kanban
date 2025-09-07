package manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import converter.CSVTaskConverter;
import model.Epic;
import model.SubTask;
import model.Task;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private String filePath;
    private boolean autoSave = true; // Флаг для автоматического сохранения

    public FileBackedTaskManager(String filePath) {
        if (filePath == null) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        this.filePath = filePath;
    }

    private String getTaskLine(Task task) {
        if (task instanceof SubTask) {
            SubTask subTask = (SubTask) task;
            return String.format("%d,%s,%s,%s,%s,%d",
                    task.getId(),
                    subTask.getType(),
                    task.getName(),
                    task.getStatus(),
                    task.getDescription(),
                    subTask.getEpicId());
        } else {
            return String.format("%d,%s,%s,%s,%s",
                    task.getId(),
                    task.getType(),
                    task.getName(),
                    task.getStatus(),
                    task.getDescription());
        }
    }

    public void save() throws ManagerSaveException {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("id,type,name,status,description,epic"); // Заголовки столбцов

            // Сохраняем задачи и эпики
            for (Task task : getTasks()) {
                lines.add(getTaskLine(task));
            }
            for (Epic epic : getEpics()) {
                lines.add(getTaskLine(epic));
            }

            // Сохраняем подзадачи
            for (SubTask subTask : getSubTasks()) {
                lines.add(getTaskLine(subTask));
            }

            Files.writeString(Path.of(filePath), lines.stream().collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении данных", e);
        }
    }

    public static class ManagerSaveException extends Exception {
        public ManagerSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) throws IOException {
        if (file == null) {
            throw new IllegalArgumentException("File cannot be null");
        }
        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        FileBackedTaskManager manager = new FileBackedTaskManager(file.getPath()); // Передаем путь к файлу

        for (int i = 1; i < lines.size(); i++) { // Пропускаем первую строку с заголовками
            String line = lines.get(i);
            try {
                Task task = CSVTaskConverter.taskFromCSV(line);
                System.out.println("Парсированная задача: " + task); // Отладочное сообщение
                if (task instanceof Task) {
                    manager.addTask((Task) task);
                } else if (task instanceof SubTask) {
                    manager.addSubTask((SubTask) task);
                } else if (task instanceof Epic) {
                    manager.addEpic((Epic) task);
                }
            } catch (Exception e) {
                System.err.println("Ошибка при парсинге строки: " + line);
                e.printStackTrace();
            }
        }

        return manager;
    }

    @Override
    public int addTask(Task task) {
        int result = super.addTask(task); // Вызываем версию метода из предка
        if (autoSave) {
            try {
                save(); // Сохраняем изменения в файл
            } catch (ManagerSaveException e) {
                System.err.println("Ошибка при сохранении данных: " + e.getMessage());
            }
        }
        return result;
    }

    @Override
    public void addSubTask(SubTask subTask) {
        super.addSubTask(subTask); // Вызываем версию метода из предка
        if (autoSave) {
            try {
                save(); // Сохраняем изменения в файл
            } catch (ManagerSaveException e) {
                System.err.println("Ошибка при сохранении данных: " + e.getMessage());
            }
        }
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic); // Вызываем версию метода из предка
        if (autoSave) {
            try {
                save(); // Сохраняем изменения в файл
            } catch (ManagerSaveException e) {
                System.err.println("Ошибка при сохранении данных: " + e.getMessage());
            }
        }
    }

    // Метод для управления автоматическим сохранением
    public void setAutoSave(boolean autoSave) {
        this.autoSave = autoSave;
    }
}