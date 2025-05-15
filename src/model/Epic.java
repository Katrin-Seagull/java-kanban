package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Epic extends Task {
    private ArrayList<Integer> idSubs = new ArrayList<>();

    public Epic(String name, String description) {
        super(name, description);
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
}
