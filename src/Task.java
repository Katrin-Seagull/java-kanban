public class Task {

    private int id;
    private String name;
    private String info;
    private Status status;
    TaskManager taskManager = new TaskManager();

    public Task(String name, String info, int id, Status status) {
        this.info = info;
        this.id = taskManager.getNewId();
        this.name = name;
        this.status = Status.NEW;
    }
}
