package manager;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager{
    private List<Task> history = new ArrayList<>();

    @Override
    public void addHistory(Task task) {
        if (history.size() == 10) {
            history.remove(0); // Удаляем самый старый элемент
        }
        history.add(task); // Добавляем задачу в конец списка
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }
}

