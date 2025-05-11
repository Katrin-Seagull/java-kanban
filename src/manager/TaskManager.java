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
    private static int id = 0; //не получается сделать не статичным, метод вызова в таск требует статичный метод, а он
    //Статичную переменную. Замкнутый круг....

    public void addTask(Task task) {
        int id = getNewId();
        task.setId(id);
        taskMap.put(id, task);
    }

    public void addSubTask(SubTask subTask) {
        int id = getNewId();
        subTask.setId(id);
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
        for (Epic epic : epicMap.values()) {
            epic.idSubs.clear(); // Очищаем список подзадach у эпика
            epic.setStatus(Status.NEW); // Устанавливаем статус NEW
        }
    }

    public void deleteEpics() {
        epicMap.clear();
        subTaskMap.clear();
    }

    public List<Task> getTasks() {
        return new ArrayList<>(taskMap.values());
    }

    public List<SubTask> getSubTasks(HashMap<Integer, SubTask> subTaskMap) {
        return new ArrayList<>(this.subTaskMap.values());
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
        for (SubTask subTask : getSubTasks(subTaskMap)) {
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

    public List<SubTask> getSubTasksForEpic(Epic epic) {
        return getSubTasks(subTaskMap);
    }
    private Status calculateEpicStatus(Epic epic) {
        List<SubTask> subTasks = getSubTasks(subTaskMap);
        if (subTasks.isEmpty()) { // Если у эпика нет подзадач
            return Status.NEW;
        }

        int newCount = 0;
        int doneCount = 0;

        for (SubTask subTask : subTasks) {
            if (subTask.getStatus() == Status.NEW) {
                newCount++;
            } else if (subTask.getStatus() == Status.DONE) {
                doneCount++;
            }
        }

        if (doneCount == subTasks.size()) {
            return Status.DONE;
        } else if (newCount > 0) {
            return Status.IN_PROGRESS;
        }
        return Status.NEW; // Это условие выполняется, если все подзадачи имеют статус NEW
    }

    public List<SubTask> getSubTasks(Epic epic, HashMap<Integer, SubTask> subTaskMap) {
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer id : epic.idSubs) {
            SubTask subTask = subTaskMap.get(id);
            if (subTask != null) {
                subTasks.add(subTask);
            }
        }
        return subTasks;
    }
}


