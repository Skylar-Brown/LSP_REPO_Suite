package org.howard.edu.lsp.midterm.crccards;

import java.util.*;

public class TaskManager {
    private Map<String, Task> tasks = new LinkedHashMap<>();

    /** Adds task; throws if duplicate ID */
    public void addTask(Task task) {
        if (tasks.containsKey(task.getTaskId())) {
            throw new IllegalArgumentException();
        }
        tasks.put(task.getTaskId(), task);
    }
    
    /** @return task by ID or null */
    public Task findTask(String taskId) {
        return tasks.get(taskId);
    }

    /** @return list of tasks matching status */
    public List<Task> getTasksByStatus(String status) {
        List<Task> result = new ArrayList<>();
        for (Task t : tasks.values()) {
            if (t.getStatus().equals(status)) {
                result.add(t);
            }
        }
        return result;
    }
}