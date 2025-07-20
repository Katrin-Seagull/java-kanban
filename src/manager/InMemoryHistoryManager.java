package manager;

import model.Task;
import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private static final int MAX_HISTORY_SIZE = 10;
    private List<Task> history = new ArrayList<>();

    @Override
    public void addHistory(Task task) {
        if (task == null) {
            return;
        }
        if (history.size() == MAX_HISTORY_SIZE) {
            history.remove(0); // Удаляем самый старый элемент
        }
        history.add(task); // Добавляем задачу в конец списка
    }

    @Override
    public List<Task> getHistory() {
        return history;
    }

}

