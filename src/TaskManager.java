import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> taskList;
    HashMap<Integer, SubTask> subTaskList;
    HashMap<Integer, Epic> epicList;

    public static int id = 0;

    public TaskManager(){
        taskList  = new HashMap<>();
        subTaskList = new HashMap<>();
        epicList = new HashMap<>();
    }

    public void  AddTask (String name, String info) {
         id = getNewId();
         Status status = Status.NEW;
         Task task = new Task(name, info, id, status);
         taskList.put(id, task);
    }
    public void  AddSubTask (String name, String info) {
        id = getNewId();
        Status status = Status.NEW;
        Task task = new Task(name, info, id, status);
        taskList.put(id, task);
    }
    public void  AddEpic (String name, String info) {
        id = getNewId();
        Status status = Status.NEW;
        Task task = new Task(name, info, id, status);
        taskList.put(id, task);
    }


    public void AllClear() {
        taskList.clear();
        subTaskList.clear();
        epicList.clear();
    }
    public void PrintAllTasks(){
        System.out.println(taskList);
        System.out.println(subTaskList);
        System.out.println(epicList);
    }
    public void PrintTask(Integer ID){
        System.out.println(taskList.get(ID));
    }
    public void PrintSubTask(Integer ID){
        System.out.println(subTaskList.get(ID));
    }
    public void PrintEpic(Integer ID){
        System.out.println(epicList.get(ID));
    }
    public void RemoveTask(Integer ID){
        taskList.remove(ID);
    }
    public void RemoveSubTask(Integer ID){
        subTaskList.remove(ID);
    }
    public void RemoveEpic(Integer ID){
        epicList.remove(ID);
    }

    public int getNewId() {
        return id++;
    }
}


