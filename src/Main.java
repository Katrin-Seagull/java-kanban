import java.util.Scanner;

public class Main {
    static TaskManager tm;
    static Scanner scanner;

    public static void main(String[] args) {

        System.out.println("Программа пока недоделана. Подскажите, пожалуйста, как можно улучшить");

        tm = new TaskManager();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    tm.PrintAllTasks();
                    break;
                case "2":
                    tm.AllClear();
                    System.out.println("Все задачи удалены.");
                    break;
                case "3":
                    lookTask();
                    break;
                case "4":
                    createTask();
                    break;
                case "5":
                    changeTask();
                    break;
                case "6":
                    deleteTask();
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Неверный ввод");
            }
        }
    }

    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Посмотреть список задач");
        System.out.println("2 - Удалить все задачи");
        System.out.println("3 - Просмотреть задачу");
        System.out.println("4 - Добавить новую задачу");
        System.out.println("5 - Изменить задачу/статус");
        System.out.println("6 - Удалить задачу");
        System.out.println("7 - Выход");
    }
    private static void printTaskMenu() {
        System.out.println("Выберите тип задач:");
        System.out.println("1 - Задача");
        System.out.println("2 - Подзадача");
        System.out.println("3 - Эпик");
        System.out.println("4 - <- Вернуться в меню");
    }

        private static void createTask() {

        System.out.println("Введите название задачи:");
        String name = scanner.nextLine();

        System.out.println("Введите содержание задачи:");
        String info = scanner.nextLine();
        tm.AddTask (name, info);
        System.out.println("Created!");
    }
    private static void changeTask(){
        System.out.println("Name?");
        String Task = scanner.nextLine();

        System.out.println("Status?");
        String Status = scanner.nextLine();

        System.out.println("Name?");
        String Name = scanner.nextLine();

        System.out.println("Info?");
        String Info = scanner.nextLine();

        System.out.println("Changed!");
    }
    private static void lookTask() {
        System.out.println("ID?");
        Integer ID = scanner.nextInt();
        tm.PrintTask(ID);
    }
    private static void deleteTask(){
        System.out.println("ID?");
        Integer ID = scanner.nextInt();
        tm.RemoveTask(ID);
        System.out.println("Deleted!");
    }
}

