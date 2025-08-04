package model;

public class Task {

    int id;
    private String name;
    private String description;
    private Status status;

    public Task(String name, String description) {
        this.description = description;
        this.name = name;
        this.status = Status.NEW;
    }

    public static Task fromString(String value) {
        String[] parts = value.split(",");
        int id = Integer.parseInt(parts[0]);
        TaskType type = TaskType.valueOf(parts[1]);
        String name = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];
        int epicId = Integer.parseInt(parts[5]);

        switch (type) {
            case TASK:
                return new Task(name, description);
            case SUBTASK:
                return new SubTask(name, description, epicId);
            case EPIC:
                return new Epic(name, description);
            default:
                throw new IllegalArgumentException("Unknown task type: " + type);
        }
    }

    public Task copy() {
        return new Task(this.name, this.description);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}