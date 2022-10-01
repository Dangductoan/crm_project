package cybersoft.java18.crm.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JobModel {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
