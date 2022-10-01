package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.model.JobModel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobRepository extends AbstractRepository<JobModel>{
    public List<JobModel> getAllJob() {
        String query = "select * from jobs";
        return executeQuery(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<JobModel> jobs = new ArrayList<>();
            while(resultSet.next()) {
                JobModel jobModel = new JobModel();
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setName(resultSet.getString("name"));
                jobModel.setEndDate(resultSet.getDate("end_date") != null
                        ? resultSet.getDate("end_date").toLocalDate().atStartOfDay()
                        : null
                );
                jobModel.setStartDate(resultSet.getDate("start_date") != null
                        ? resultSet.getDate("start_date").toLocalDate().atStartOfDay()
                        : null
                );

                jobs.add(jobModel);

            }
            return jobs;
        });
    }
    public JobModel getJobById(String id) {
        String query = "select * from jobs where id =?";
        return executeQuery2( connection -> {
            JobModel jobModel = new JobModel();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                jobModel.setName(resultSet.getString("name"));
                jobModel.setId(resultSet.getInt("id"));
                jobModel.setEndDate(resultSet.getDate("end_date") != null
                        ? resultSet.getDate("end_date").toLocalDate().atStartOfDay()
                        : null
                );
                jobModel.setStartDate(resultSet.getDate("start_date") != null
                        ? resultSet.getDate("start_date").toLocalDate().atStartOfDay()
                        : null
                );
                return jobModel;

            }
            return jobModel;

        });
    }
    public Integer deleteJob(String id) {
        return executeSaveAndUpdate(connection -> {
            String query = "delete from jobs where id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            return preparedStatement.executeUpdate();
        });
    }
    public Integer updateJob(JobModel jobModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "update jobs set name=?,start_date=?,end_date=? WHERE id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,jobModel.getName());
            preparedStatement.setDate(2, Date.valueOf(jobModel.getStartDate().toLocalDate()));
            preparedStatement.setDate(3, Date.valueOf(jobModel.getEndDate().toLocalDate()));
            preparedStatement.setInt(4,jobModel.getId());
            return preparedStatement.executeUpdate();
        });
    }
    public Integer createJob(JobModel jobModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "insert into jobs(name,start_date,end_date) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, jobModel.getName());
            preparedStatement.setDate(2, Date.valueOf(jobModel.getStartDate().toLocalDate()));
            preparedStatement.setDate(3, Date.valueOf(jobModel.getEndDate().toLocalDate()));
            return preparedStatement.executeUpdate();
        });
    }



}
