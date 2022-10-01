package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.respository.JobRepository;

import java.util.List;

public class JobServices {
    private static JobServices INSTANCE = null;

    public static JobServices getInstance() {
        if(INSTANCE == null)
            INSTANCE = new JobServices();
        return INSTANCE;
    }
    private final JobRepository jobRepository = new JobRepository();

    public List<JobModel> getAllJob() {
        return jobRepository.getAllJob();
    }
    public JobModel getJobById(String id) {return jobRepository.getJobById(id);}
    public Integer deleteJobById(String id) {
        return jobRepository.deleteJob(id);
    }
    public Integer updateJobById(JobModel jobModel) {return jobRepository.updateJob(jobModel);}
    public Integer createJob(JobModel jobModel) {return jobRepository.createJob(jobModel);}
}
