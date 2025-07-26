package manager;
import model.Task;
import java.util.List;

public interface HistoryManager {

    void addHistory(Task task);
    List<Task> getHistory();
}