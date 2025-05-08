package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Epic extends Task {
    protected ArrayList<Integer> idSubs;

    public Epic(String name, String description, int id, Status status) {
        super(name, description);
        idSubs = new ArrayList<>(); // Инициализация списка
    }

    // Метод для добавления id подзадачи в список
    public void addSubTaskId(int id) {
        idSubs.add(id);
    }
    // Метод для получения списка подзадач
    public List<SubTask> getSubTasks(HashMap<Integer, SubTask> subTaskMap) {
        List<SubTask> subTasks = new ArrayList<>();
        for (Integer id : idSubs) {
            SubTask subTask = subTaskMap.get(id);
            if (subTask != null) {
                subTasks.add(subTask);
            }
        }
        return subTasks;
    }
}
