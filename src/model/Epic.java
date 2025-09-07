package model;

import java.util.ArrayList;

public class Epic extends Task {
    private ArrayList<Integer> idSubs = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
        this.setId(id);
        this.setStatus(Status.NEW); // Устанавливаем статус NEW по умолчанию
    }

    // Метод для добавления id подзадачи в список
    public void addSubTaskId(int id) {
        idSubs.add(id);
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Integer> getIdSubs() {
        return idSubs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Epic epic = (Epic) o;
        return id == epic.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public TaskType getType() {
        return TaskType.EPIC;
    }
}