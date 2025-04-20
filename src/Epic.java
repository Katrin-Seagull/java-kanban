public class Epic extends Task{
    private int id;
    private String name;
    private String info;
    private Status status;
    TaskManager taskManager = new TaskManager();

    public Epic(String name, String info, int id, Status status) {
        super(name, info, id, status);
    }
}
