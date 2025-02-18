package com.api.task.service;

import com.api.task.model.Task;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskConsumer {

    @Autowired
    private TaskService taskService;

    @RabbitListener(queues = "taskQueue")
    public void receiveMessage(Task task) {
        System.out.println("Mensagem recebida: " + task);
        taskService.createTask(task); // Salva a tarefa e envia o e-mail
    }
}
