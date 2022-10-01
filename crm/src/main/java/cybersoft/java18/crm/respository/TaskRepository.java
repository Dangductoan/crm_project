package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.TaskModel;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TaskRepository extends AbstractRepository<TaskModel> {
    public List<TaskModel> getAllTask() {

        String query = "select * from tasks";
       return  executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<TaskModel> tasks = new ArrayList<>();
            while (resultSet.next()) {
                TaskModel taskModel = new TaskModel();
                taskModel.setId(resultSet.getInt("id"));
                taskModel.setName(resultSet.getString("name"));
                taskModel.setStartDate(resultSet.getDate("start_date") != null
                        ? resultSet.getDate("start_date").toLocalDate().atStartOfDay()
                        : null
                );
                taskModel.setEndDate(resultSet.getDate("end_date") != null
                        ? resultSet.getDate("end_date").toLocalDate().atStartOfDay()
                        : null
                );
                taskModel.setJobId(resultSet.getInt("job_id"));
                taskModel.setUserId(resultSet.getInt("user_id"));
                taskModel.setStatusId(resultSet.getInt("status_id"));

                tasks.add(taskModel);
            }
            return tasks;
        });
    }
    public Integer deleteTask(String id) {
        return executeSaveAndUpdate(connection -> {
            String query = "delete from tasks where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate();
        });
    }
    public Integer updateTask(TaskModel taskModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "update tasks set name=?,start_date=?,end_date=?,user_id=?,job_id=?,status_id=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,taskModel.getName());
            preparedStatement.setDate(2, Date.valueOf(taskModel.getStartDate().toLocalDate()));
            preparedStatement.setDate(3, Date.valueOf(taskModel.getEndDate().toLocalDate()));
            preparedStatement.setInt(4,taskModel.getUserId());
            preparedStatement.setInt(5,taskModel.getJobId());
            preparedStatement.setInt(6,taskModel.getStatusId());
            preparedStatement.setInt(7,taskModel.getId());
            return preparedStatement.executeUpdate();
        });
    }
    public Integer createTask(TaskModel taskModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "insert into tasks(name,start_date,end_date,user_id,job_id,status_id) values(?,?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,taskModel.getName());
            preparedStatement.setDate(2, Date.valueOf(taskModel.getStartDate().toLocalDate()));
            preparedStatement.setDate(3, Date.valueOf(taskModel.getEndDate().toLocalDate()));
            preparedStatement.setInt(4,taskModel.getUserId());
            preparedStatement.setInt(5,taskModel.getJobId());
            preparedStatement.setInt(6,taskModel.getStatusId());
            return preparedStatement.executeUpdate();
        });
    }
}
