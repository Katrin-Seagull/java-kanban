package manager;

import model.Task;

class Node {
    int taskId;
    Task task; // Добавляем поле для полной задачи
    Node prev;
    Node next;

    Node(int taskId, Task task) {
        this.taskId = taskId;
        this.task = task; // Сохраняем полную задачу
    }
}