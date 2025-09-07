package manager;

public class Managers {
    public static TaskManager getDefault() {
        String filePath = "data/tasks.csv"; // укажите путь к файлу
        return new FileBackedTaskManager("path/to/file.txt");
    }


    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}