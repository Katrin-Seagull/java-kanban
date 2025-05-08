package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    protected HashMap<Integer, Task> taskMap = new HashMap<>();
    protected HashMap<Integer, SubTask> subTaskMap = new HashMap<>();
    protected HashMap<Integer, Epic> epicMap = new HashMap<>();
    private static int id = 0;

    public void addTask(Task task) {
        int id = getNewId();
        task.setId(id);
        task.setStatus(Status.NEW);
        taskMap.put(id, task);
    }

    public void addSubTask(SubTask subTask) {
        int id = getNewId();
        subTask.setId(id);
        subTask.setStatus(Status.NEW);
        taskMap.put(id, subTask);
    }

    public void addEpic(Epic epic) {
        int id = getNewId();
        epic.setId(id);
        epic.setStatus(Status.NEW);
        taskMap.put(id, epic);
    }

    public void deleteTasks() {
        taskMap.clear();
    }

    public void deleteSubTasks() {
        subTaskMap.clear();
    }

    public void deleteEpics() {
        epicMap.clear();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public List<SubTask> getSubTasks() {
        return new ArrayList<>(subTaskMap.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }

    public Task getTask(Integer ID) {
        return taskMap.get(ID);
    }

    public SubTask getSubTask(Integer ID) {
        return subTaskMap.get(ID);
    }

    public Epic getEpic(Integer ID) {
        return epicMap.get(ID);
    }

    public void removeTask(Integer ID) {
        taskMap.remove(ID);
    }

    public void removeSubTask(Integer ID) {
        subTaskMap.remove(ID);
    }

    public void removeEpic(Integer ID) {
        Epic epic = epicMap.get(ID);
        for (SubTask subTask : epic.getSubTasks(subTaskMap)) {
            subTaskMap.remove(subTask.getId());
        }
        epicMap.remove(ID);
    }

    public static int getNewId() {
        return id++;
    }

    public void updateTask(Task newTask) {
        Integer id = newTask.getId();
        taskMap.put(id, newTask);
    }

    public void updateSubTask(SubTask newSubTask) {
        Integer id = newSubTask.getId();
        subTaskMap.put(id, newSubTask);
    }

    public void updateEpic(Epic newEpic) {
        Integer id = newEpic.getId();
        epicMap.put(id, newEpic);
    }

    public void updateTaskStatus(Integer taskId, Status newStatus) {
        Task task = taskMap.get(taskId);
        task.setStatus(newStatus);
        taskMap.put(taskId, task); // Обновляем задачу в map с новым статусом
    }

    public void updateSubTaskStatus(Integer subTaskId, Status newStatus) {
        SubTask subTask = subTaskMap.get(subTaskId);
        subTask.setStatus(newStatus);
        taskMap.put(subTaskId, subTask); // Обновляем задачу в map с новым статусом
    }

    public void updateEpicStatusBySubTasks(Integer epicId) {
        Epic epic = epicMap.get(epicId);
        Status newStatus = calculateEpicStatus(epic);
        updateEpicStatus(epicId, newStatus);
    }

    public void updateEpicStatus(Integer epicId, Status newStatus) {
        Epic epic = epicMap.get(epicId);
        epic.setStatus(newStatus);
        taskMap.put(epicId, epic); // Обновляем задачу в map с новым статусом

    }
    public List<SubTask> getSubTasksForEpic(Epic epic) {
        return epic.getSubTasks(subTaskMap);
    }
    public Status calculateEpicStatus(Epic epic) {
        List<SubTask> subTasks = epic.getSubTasks(subTaskMap);
        if (subTasks.isEmpty()) { // Если у эпика нет подзадач
            return Status.NEW;
        }
        boolean allDone = true;
        boolean anyNotDone = false;
        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() != Status.DONE) {
                allDone = false;
                anyNotDone = true;
                break; // Можно выйти из цикла, как только найдена подзадача не со статусом DONE
            }
        }
        if (allDone) {
            return Status.DONE;
        } else if (anyNotDone) {
            return Status.IN_PROGRESS;
        }
        return Status.NEW; // Это условие выполняется, если все подзадачи имеют статус NEW
    }
}


