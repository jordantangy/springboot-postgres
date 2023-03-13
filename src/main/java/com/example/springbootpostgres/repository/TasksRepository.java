package com.example.springbootpostgres.repository;

import com.example.springbootpostgres.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.List;

public interface TasksRepository extends JpaRepository<Task,String> {

        List<Task> findByImportance(int imp);

}
