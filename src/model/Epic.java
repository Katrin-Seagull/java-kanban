public class Epic extends Task{
    private int idSub;
    public Epic(String name, String description, int id, Status status,int idSub) {
        super(name, description, id, status);
    }
}
