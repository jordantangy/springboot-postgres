package com.example.springbootpostgres.service;

import com.example.springbootpostgres.model.Task;
import com.example.springbootpostgres.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    @Autowired
    private TasksRepository repository;

    public Task addTask(Task task){
        return repository.save(task);
    }

    public List<Task> findAllTasks(){
       return repository.findAll();
    }

    public Task getTaskById(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskImp(int imp){
        return repository.findByImportance(imp);
    }

    public Task updateTask(String tid,Task task){
        Task exisitingTask = getTaskById(tid);
        exisitingTask.setDescription(task.getDescription());
        exisitingTask.setAssignee(task.getAssignee());
        exisitingTask.setImportance(task.getImportance());
        return repository.save(exisitingTask);
    }

    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId+" task deleted successfully";
    }


}
