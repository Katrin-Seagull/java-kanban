package converter;

import model.Task;
import model.SubTask;
import model.Epic;
import model.Status;

public class CSVTaskConverter {
    public static String taskToCSV(Task task) {
        if (task instanceof SubTask) {
            SubTask subTask = (SubTask) task;
            return String.format("%d,SUBTASK,%s,%s,%s,%d", subTask.getId(), subTask.getName(), subTask.getStatus(), subTask.getDescription(), subTask.getEpicId());
        } else if (task instanceof Epic) {
            Epic epic = (Epic) task;
            return String.format("%d,EPIC,%s,%s,%s", epic.getId(), epic.getName(), epic.getStatus(), epic.getDescription());
        } else {
            return String.format("%d,TASK,%s,%s,%s", task.getId(), task.getName(), task.getStatus(), task.getDescription());
        }
    }

    public static Task taskFromCSV(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid task format");
        }

        try {
            int id = Integer.parseInt(parts[0]);
            String type = parts[1];
            String name = parts[2];
            Status status = Status.valueOf(parts[3]);
            String description = parts[4];
            int epicId = Integer.parseInt(parts[5]);

            if ("SUBTASK".equals(type)) {
                SubTask subTask = new SubTask(name, description, epicId);
                return subTask;
            } else if ("EPIC".equals(type)) {
                Epic epic = new Epic(name, description);
                return epic;
            } else {
                Task task = new Task(name, description);
                task.setId(id);
                task.setStatus(status);
                return task;
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Error parsing task ID: " + e.getMessage(), e);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Error parsing task: " + e.getMessage(), e);
        }
    }
}