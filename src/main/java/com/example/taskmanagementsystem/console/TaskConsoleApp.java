package com.example.taskmanagementsystem.console;

import com.example.taskmanagementsystem.dto.TaskDto;
import com.example.taskmanagementsystem.dto.UserDto;
import com.example.taskmanagementsystem.exception.*;
import com.example.taskmanagementsystem.service.TaskService;
import com.example.taskmanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;
@Component
public class TaskConsoleApp {
    @Autowired
    private TaskService taskService;
    @Autowired
    private UserService userService;
    public void run() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Добро пожаловать в Task Management System!");
        System.out.println("Введите 'help' для списка доступных команд.");

        while (true) {
            System.out.print("\nВведите команду: ");
            String command = scanner.nextLine().trim();

            switch (command.toLowerCase()) {
                case "create_task":
                    createTask(scanner);
                    break;
                case "get_all_tasks":
                    getAllTasks(scanner);
                    break;
                case "update_task":
                    updateTask(scanner);
                    break;
                case "delete_task":
                    deleteTask(scanner);
                    break;
                case "create_user":
                    createUser(scanner);
                break;
                case "delete_user":
                    deleteUser(scanner);
                break;
                case "next_status_task":
                    nextStatusTask(scanner);
                    break;
                case "help":
                    printHelp();
                    break;
                case "exit":
                    System.out.println("Завершение работы.");
                    return;
                default:
                    System.out.println("Неизвестная команда.");
            }
        }
    }

    private void createTask(Scanner scanner) {
        System.out.print("Введите название задачи: ");
        String title = scanner.nextLine();
        System.out.print("Введите описание задачи: ");
        String description = scanner.nextLine();
        System.out.print("Введите создателя: ");
        String creator = scanner.nextLine();
        System.out.print("Введите назначенного пользователя: ");
        String assignee = scanner.nextLine();

        TaskDto taskDto = new TaskDto(title,description,creator,assignee);
        try {
            System.out.println("\nЗадача создана: " + taskService.createTask(taskDto));
        } catch (TitleIsNullException exception) {
            System.out.println("Название задачи не может быть пустым");
        }
    }

    private void getAllTasks(Scanner scanner) {
        System.out.println("Будет выведен список задач отсортированный по дате создания.");
        System.out.print("Введите 'desc' для обратной сортировки (или нажмите Enter, чтобы пропустить): ");
        String sortOrder = scanner.nextLine();
        List<TaskDto> tasks = taskService.listAll(sortOrder) ;
        if (tasks.isEmpty()) {
            System.out.println("Нет задач.");
        } else {
            tasks.forEach(System.out::println);
        }
    }

    private void updateTask(Scanner scanner) {
        System.out.print("Введите ID задачи: ");
        Long id = Long.parseLong(scanner.nextLine());
        System.out.print("Введите новое название задачи (или нажмите Enter, чтобы пропустить): ");
        String title = scanner.nextLine();
        System.out.print("Введите новое описание задачи (или нажмите Enter, чтобы пропустить): ");
        String description = scanner.nextLine();
        System.out.print("Введите нового создателя (или нажмите Enter, чтобы пропустить): ");
        String creator = scanner.nextLine();
        System.out.print("Введите нового назначенного пользователя (или нажмите Enter, чтобы пропустить): ");
        String assignee = scanner.nextLine();

        TaskDto taskDto = new TaskDto(title,description,creator,assignee);
        try {
            System.out.println("\nЗадача обновлена: " + taskService.updateTaskById(id,taskDto));
        } catch (IdNotFoundException exception) {
            System.out.println("id не найдено");
        }
    }

    private void deleteTask(Scanner scanner) {
        System.out.print("Введите ID задачи для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());
        taskService.deleteTaskById(id);
        System.out.println("Задача удалена.");
    }

    private void nextStatusTask(Scanner scanner) {
        System.out.print("Введите ID задачи: ");
        Long id = Long.parseLong(scanner.nextLine());
        try {
            System.out.println("\nСтатус задачи успешно продвинут: " + taskService.nextTaskStatusById(id));
        } catch (IdNotFoundException exception) {
            System.out.println("id не найдено");
        }
    }

    private void createUser(Scanner scanner) {
        System.out.print("Введите username: ");
        String username = scanner.nextLine();
        System.out.print("Введите пароль: ");
        String password = scanner.nextLine();

        UserDto userDto = new UserDto(username,password);
        try {
            userService.createUser(userDto);
            System.out.println("Пользователь успешно зарегистрирован");
        } catch (UsernameIsNullException exception) {
            System.out.println("Username не может быть пустым");
        } catch (UsernameAlreadyExistsException exception) {
            System.out.println("Такой username уже существует");
        } catch (PasswordIsNullException exception) {
            System.out.println("Пароль не может быть пустым");
        }
    }

    private void deleteUser(Scanner scanner) {
        System.out.print("Введите ID пользователя для удаления: ");
        Long id = Long.parseLong(scanner.nextLine());
        userService.deleteUserById(id);
        System.out.println("Пользователь удален.");
    }

    private void printHelp() {
        System.out.println("Доступные команды:");
        System.out.println("  create_task - Создать задачу");
        System.out.println("  get_all_tasks - Показать все задачи, отсортированные по дате создания");
        System.out.println("  update_task - Обновить задачу");
        System.out.println("  delete_task - Удалить задачу");
        System.out.println("  next_status_task - Продвинуть статус задачи");
        System.out.println("  create_user - Создать пользователя");
        System.out.println("  delete_user - Удалить пользователя");
        System.out.println("  exit - Выход");
    }
}
