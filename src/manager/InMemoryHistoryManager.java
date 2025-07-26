package manager;

import model.Task;

import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private Node head;
    private Node tail;
    private Map<Integer, Node> map = new HashMap<>();

    @Override
    public void addHistory(Task task) {
        if (task == null) {
            return;
        }
        int taskId = task.getId();
        Node currentNode = map.get(taskId);

        // Если задача уже есть в истории и её состояние изменилось, добавляем новую версию
        if (currentNode != null && !currentNode.task.equals(task)) {
            Node node = new Node(taskId, task);
            linkLast(task);
            map.put(taskId, node);
        } else if (currentNode == null) { // Если задачи ещё нет в истории
            Node node = new Node(taskId, task);
            linkLast(task);
            map.put(taskId, node);
        }
    }

    private void removeNode(Node node) {
        if (node.prev != null) {
            node.prev.next = node.next;
        } else {
            head = node.next; // Если удаляемый узел — голова списка
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        } else {
            tail = node.prev; // Если удаляемый узел — хвост списка
        }
    }

    @Override
    public void removeHistory(int id) {
        if (map.containsKey(id)) {
            Node node = map.get(id);
            removeNode(node);
            map.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        List<Task> history = new ArrayList<>();
        Node current = head;
        while (current != null) {
            history.add(current.task); // Добавляем полную задачу из узла
            current = current.next;
        }
        return history;
    }

    private void linkLast(Task task) {
        Node node = new Node(task.getId(), task);
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        } else {
            head = node; // Если список был пуст
        }
        tail = node;
    }
}