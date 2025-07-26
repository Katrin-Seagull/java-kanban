package manager;

import model.Task;
import model.Epic;
import model.SubTask;
import java.util.List;

public interface TaskManager {
    int addTask(Task task);
    void addSubTask(SubTask subTask);
    void addEpic(Epic epic);
    void deleteTasks();
    void deleteSubTasks();
    void deleteEpics();
    List<Task> getTasks();
    List<SubTask> getSubTasks();
    List<Epic> getEpics();
    Task getTask(Integer ID);
    SubTask getSubTask(Integer ID);
    Epic getEpic(Integer ID);
    void removeTask(Integer ID);
    void removeSubTask(Integer ID);
    void removeEpic(Integer ID);
    void updateTask(Task newTask);
    void updateSubTask(SubTask newSubTask);
    void updateEpic(Epic newEpic);
}