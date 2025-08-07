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

    public String toString() {
        return String.format("%d,%s,%s,%s,%s,%d", id, name, status, description);
    }


    public static Task fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Invalid task format");
        }

        TaskType type = TaskType.valueOf(parts[1]);
        int id = Integer.parseInt(parts[0]);
        String name = parts[2];
        Status status = Status.valueOf(parts[3]);
        String description = parts[4];
        int epicId = Integer.parseInt(parts[5]);

        switch (type) {
            case TASK:
                return new Task(name, description);
            case EPIC:
                return new Epic(name, description);
            case SUBTASK:
                return new SubTask(name, description, epicId);
            default:
                throw new IllegalArgumentException("Unknown task type");
        }
    }
}