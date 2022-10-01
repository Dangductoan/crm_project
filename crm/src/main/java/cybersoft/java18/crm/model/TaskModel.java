package cybersoft.java18.crm.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskModel {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int userId;
    private int statusId;
    private int jobId;
}
