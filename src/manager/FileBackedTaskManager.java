package manager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import model.Epic;
import model.SubTask;
import model.Task;

public class FileBackedTaskManager extends InMemoryTaskManager {
    private String filePath;

    public FileBackedTaskManager(String filePath) {
        this.filePath = filePath;
    }

    public void save() {
        try {
            List<String> lines = new ArrayList<>();
            lines.add("id,type,name,status,description,epic"); // Заголовки столбцов

            // Сохраняем задачи
            for (Task task : getTasks()) {
                lines.add(String.format("%d,TASK,%s,%s,%s,", task.getId(), task.getName(), task.getStatus(), task.getDescription()));
            }

            // Сохраняем подзадачи
            for (SubTask subTask : getSubTasks()) {
                lines.add(String.format("%d,SUBTASK,%s,%s,%s,%d", subTask.getId(), subTask.getName(), subTask.getStatus(), subTask.getDescription(), subTask.getEpicId()));
            }

            // Сохраняем эпики
            for (Epic epic : getEpics()) {
                lines.add(String.format("%d,EPIC,%s,%s,%s,", epic.getId(), epic.getName(), epic.getStatus(), epic.getDescription()));
            }

            Files.writeString(Path.of(filePath), lines.stream().collect(Collectors.joining("\n")));
        } catch (IOException e) {
            throw new ManagerSaveException("Ошибка при сохранении данных", e);
        }
    }

    // Определение вашего непроверяемого исключения
    class ManagerSaveException extends RuntimeException {
        public ManagerSaveException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    public static FileBackedTaskManager loadFromFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(file.getPath()));
        FileBackedTaskManager manager = new FileBackedTaskManager(file.getPath()); // Передаем путь к файлу

        for (int i = 1; i < lines.size(); i++) { // Пропускаем первую строку с заголовками
            Task task = Task.fromString(lines.get(i));
            manager.addTask(task); // Предполагаем, что addTask умеет добавлять разные типы задач
        }

        return manager;
    }
<<<<<<< HEAD
}
=======
}

>>>>>>> 08b8d8aefa13c0c4e5be00f2274946ade039f3ba
