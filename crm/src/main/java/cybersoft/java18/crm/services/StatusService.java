package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.StatusModel;
import cybersoft.java18.crm.respository.StatusRepository;
import cybersoft.java18.crm.respository.TaskRepository;

import java.util.Optional;

public class StatusService {
    private static StatusService INSTANCE = null;
    public static StatusService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new StatusService();
        }
        return INSTANCE;
    }
    private final StatusRepository statusRepository = new StatusRepository();
    public StatusModel getStatusById(String id) {
        return statusRepository.getStatusById(id);
    }
}
