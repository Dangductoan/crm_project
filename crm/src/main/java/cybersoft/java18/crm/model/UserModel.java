package cybersoft.java18.crm.model;

import lombok.Data;

@Data
public class UserModel {
    private int id;
    private String email;
    private String password;
    private String fullName;
    private String avatar;
    private int roleId;
}
