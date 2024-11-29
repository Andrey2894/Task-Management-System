package com.example.taskmanagementsystem;

import com.example.taskmanagementsystem.console.TaskConsoleApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class TaskManagementSystemApplication {
	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(TaskManagementSystemApplication.class, args);
		TaskConsoleApp app = context.getBean(TaskConsoleApp.class);
		app.run();
	}
}
