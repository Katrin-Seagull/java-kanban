package model;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Epic extends Task {
    public ArrayList<Integer> idSubs = new ArrayList<>();

    public Epic(String name, String description, int id, Status status) {
        super(name, description);
    }

    // Метод для добавления id подзадачи в список
    public void addSubTaskId(int id) {
        idSubs.add(id);
    }
    // Метод для получения списка подзадач

}
