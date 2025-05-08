public class SubTask extends Task{
    private int idEpic;

    public SubTask(String name, String description, int id, Status status,int idEpic) {
        super(name, description, id, status);
    }
}
