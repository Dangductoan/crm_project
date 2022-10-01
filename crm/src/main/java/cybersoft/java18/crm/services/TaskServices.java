package cybersoft.java18.crm.services;


import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.respository.TaskRepository;

import java.util.List;

public class TaskServices {
    private static TaskServices INSTANCE = null;
    public static TaskServices getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TaskServices();
        }
        return INSTANCE;
    }
    private final TaskRepository taskRepository = new TaskRepository();
    public List<TaskModel> getAllTask() {
        return taskRepository.getAllTask();
    }
    public Integer deleteTaskById(String id) {
        return taskRepository.deleteTask(id);
    }
    public Integer updateTaskById(TaskModel taskModel) {return taskRepository.updateTask(taskModel);}
    public Integer createTask(TaskModel taskModel) {return taskRepository.createTask(taskModel);}
}
