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
}