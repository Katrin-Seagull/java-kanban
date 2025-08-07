package test;

import manager.FileBackedTaskManager;
import model.Epic;
import model.SubTask;
import model.Task;

import java.io.File;
import java.io.IOException;

public class FileBackedTaskManagerTest {
    public static void main(String[] args) throws IOException {
        testEmptySaveLoad();
        testSaveLoadMultipleTasks();
    }

    private static void testEmptySaveLoad() throws IOException {
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit(); // Файл будет удален после завершения программы

        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile.getPath());
        manager.save(); // Сохраняем пустой менеджер

        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);
        // Проверяем, что загруженный менеджер пуст
        System.out.println("Test empty save/load: " + (loadedManager.getTasks().isEmpty() ? "passed" : "failed"));
    }

    private static void testSaveLoadMultipleTasks() throws IOException {
        File tempFile = File.createTempFile("test", ".csv");
        tempFile.deleteOnExit();

        FileBackedTaskManager manager = new FileBackedTaskManager(tempFile.getPath());
        // Добавляем несколько задач
        manager.addTask(new Task("Task 1", "Description 1"));
        Epic epic = new Epic("Epic 1", "Description E1"); // Сохраняем эпик в переменную
        manager.addEpic(epic);
        int epicId = epic.getId();// Получаем идентификатор эпика

        manager.addTask(new SubTask("SubTask 1", "Description S1", epicId));

        manager.save();

        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(tempFile);
        // Проверяем, что загруженный менеджер содержит те же задачи
        System.out.println("Test save/load multiple tasks: " + (loadedManager.getTasks().size() == 3 ? "passed" : "failed"));
    }
}