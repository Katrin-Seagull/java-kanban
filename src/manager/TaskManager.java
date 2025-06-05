package manager;

import model.Task;
import model.Epic;
import model.SubTask;
import manager.InMemoryTaskManager;
import java.util.List;

public interface TaskManager {
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();


}


