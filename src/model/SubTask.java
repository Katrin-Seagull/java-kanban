package model;

public class SubTask extends Task {
    private int epicId;

    public SubTask(String name, String description, int id, Status status, int epicId) {
        super(name, description);
        this.epicId = epicId;
    }
    public int getEpicId() {
        return epicId;
    }
}
