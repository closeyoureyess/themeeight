package com.effective.themeeight.repositories;

import com.effective.themeeight.entities.Task;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Интерфейс-репозиторий с кастомными методами для task
 */
@Repository
public interface TaskRepository extends JdbcRepository<Long, Task> {

    Optional<List<Task>> findAllByTitle(String title, Integer offset, Integer limit);

}
