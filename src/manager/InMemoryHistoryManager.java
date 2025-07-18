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
        Node node = new Node(taskId, task); // Создаем новый узел для задачи

        // Если задача уже есть в истории, удаляем старый узел
        if (map.containsKey(taskId)) {
            Node oldNode = map.get(taskId);
            removeNode(oldNode);
        }

        // Добавляем новый узел в хвост списка
        addNodeToTail(node);

        // Сохраняем узел в HashMap с уникальным идентификатором
        map.put(taskId, node);
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

    private void addNodeToTail(Node node) {
        if (tail != null) {
            tail.next = node;
            node.prev = tail;
        } else {
            head = node; // Если список был пуст
        }
        tail = node;
    }

    private void removeOldest() {
        Node nodeToRemove = head;
        if (head != null) {
            if (head.next != null) {
                head = head.next;
                head.prev = null;
            } else {
                // Если в списке только один элемент, устанавливаем head и tail в null
                head = null;
                tail = null;
            }
            map.remove(nodeToRemove.taskId); // Удаляем соответствующий элемент из HashMap
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
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        Node current = head;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }

}
