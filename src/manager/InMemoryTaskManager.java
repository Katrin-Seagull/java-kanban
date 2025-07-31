package manager;

import model.Epic;
import model.SubTask;
import model.Task;
import model.Status;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    protected HashMap<Integer, Task> taskMap = new HashMap<>();
    protected HashMap<Integer, SubTask> subTaskMap = new HashMap<>();
    protected HashMap<Integer, Epic> epicMap = new HashMap<>();
    private int id = 0;
    private HistoryManager historyManager = Managers.getDefaultHistory();

    public int addTask(Task task) {
        int id = getNewId();
        task.setId(id);
        taskMap.put(id, task);
        return id;
    }

    public void addSubTask(SubTask subTask) {
        int id = getNewId();
        subTask.setId(id);
        subTaskMap.put(id, subTask);
    }

    public void addEpic(Epic epic) {
        int id = getNewId();
        epic.setId(id);
        epic.setStatus(Status.NEW);
        epicMap.put(id, epic);
    }

    public void deleteTasks() {
        taskMap.clear();
    }

    public void deleteSubTasks() {
        subTaskMap.clear();
        for (Epic epic : epicMap.values()) {
            epic.getIdSubs().clear(); // Очищаем список подзадach у эпика
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

    public List<SubTask> getSubTasks() {
        return new ArrayList<>(this.subTaskMap.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epicMap.values());
    }

    public Task getTask(Integer id) {
        Task task = taskMap.get(id);
        historyManager.addHistory(task);
        return task;
    }

    public SubTask getSubTask(Integer id) {
        SubTask subTask = subTaskMap.get(id);
        historyManager.addHistory(subTask);
        return subTask;
    }

    public Epic getEpic(Integer id) {
        Epic epic = epicMap.get(id);
        historyManager.addHistory(epic);
        return epic;
    }

    public void removeTask(Integer id) {
        taskMap.remove(id);
        historyManager.removeHistory(id); // Добавляем вызов метода для удаления из истории
    }

    public void removeSubTask(Integer id) {
        SubTask subTask = subTaskMap.remove(id);
        Epic epic = getEpicForSubTask(subTask);
        epic.getIdSubs().remove((Integer) subTask.getId()); // Удаляем идентификатор подзадачи из списка эпика
        Status newStatus = calculateEpicStatus(epic); // Рассчитываем новый статус эпика
        epic.setStatus(newStatus); // Устанавливаем новый статус
        historyManager.removeHistory(id); // Добавляем вызов метода для удаления из истории
    }

    public void removeEpic(Integer id) {
        Epic epic = epicMap.get(id);
        for (SubTask subTask : getSubTasks()) {
            subTaskMap.remove(subTask.getId());
            historyManager.removeHistory(subTask.getId()); // Добавляем вызов метода для каждой подзадачи
        }
        epicMap.remove(id);
        historyManager.removeHistory(id); // Добавляем вызов метода для эпика
    }


    private int getNewId() {
        return id++;
    }

    public void updateTask(Task newTask) {
        Integer id = newTask.getId();
        taskMap.put(id, newTask);
    }

    public void updateSubTask(SubTask newSubTask) {
        Integer id = newSubTask.getId();
        subTaskMap.put(id, newSubTask);

        Epic epic = getEpicForSubTask(newSubTask);
        Status newStatus = calculateEpicStatus(epic);
        epic.setStatus(newStatus);
        updateEpic(epic);
    }

    public void updateEpic(Epic newEpic) {
        Integer id = newEpic.getId();
        Status newStatus = calculateEpicStatus(newEpic);
        newEpic.setStatus(newStatus);
        epicMap.put(id, newEpic);
    }

    private Status calculateEpicStatus(Epic epic) {
        List<SubTask> subTasks = getSubTasks();
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
            } else if (subTask.getStatus() == Status.IN_PROGRESS) {
                return Status.IN_PROGRESS;
            }
        }

        if (doneCount == subTasks.size()) {
            return Status.DONE;
        } else if (newCount == subTasks.size()) { // Проверяем, что все подзадачи NEW
            return Status.NEW;
        } else {
            return Status.IN_PROGRESS;
        }
    }

    public List<SubTask> getSubTasksForEpic(Epic epic) {
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer id : epic.getIdSubs()) {
            SubTask subTask = subTaskMap.get(id);
            if (subTask != null) {
                subTasks.add(subTask);
            }
        }
        return subTasks;
    }

    private Epic getEpicForSubTask(SubTask subTask) {
        return epicMap.get(subTask.getEpicId());
    }
}