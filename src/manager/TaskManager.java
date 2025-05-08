import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> taskMap = new HashMap<>();
    HashMap<Integer, SubTask> subTaskMap = new HashMap<>();
    HashMap<Integer, Epic> epicMap = new HashMap<>();
    public static int id = 0;

    public void  addTask (String name, String info) {
         id = getNewId();
         Status status = Status.NEW;
         Task task = new Task(name, info, id, status);
         taskMap.put(id, task);
    }
    public void  addSubTask (String name, String info) {
        id = getNewId();
        Status status = Status.NEW;
        Task task = new Task(name, info, id, status);
        taskMap.put(id, task);
    }
    public void  addEpic (String name, String info) {
        id = getNewId();
        Status status = Status.NEW;
        Task task = new Task(name, info, id, status);
        taskMap.put(id, task);
    }


    public void allClear() {
        taskMap.clear();
        subTaskMap.clear();
        epicMap.clear();
    }
    public void printAllTasks(){
        System.out.println(taskMap);
        System.out.println(subTaskMap);
        System.out.println(epicMap);
    }
    public void printTask(Integer ID){
        System.out.println(taskMap.get(ID));
    }
    public void printSubTask(Integer ID){
        System.out.println(subTaskMap.get(ID));
    }
    public void printEpic(Integer ID){
        System.out.println(epicMap.get(ID));
    }
    public void removeTask(Integer ID){
        taskMap.remove(ID);
    }
    public void removeSubTask(Integer ID){
        subTaskMap.remove(ID);
    }
    public void removeEpic(Integer ID){
        epicMap.remove(ID);
    }

    protected static int getNewId() {
        return id++;
    }
}


