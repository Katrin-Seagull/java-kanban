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

    Task getTask(Integer id);

    SubTask getSubTask(Integer id);

    Epic getEpic(Integer id);

    void removeTask(Integer id);

    void removeSubTask(Integer id);

    void removeEpic(Integer id);

    void updateTask(Task newTask);

    void updateSubTask(SubTask newSubTask);

    void updateEpic(Epic newEpic);
}