package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.respository.RoleRepsository;

import java.util.List;

public class RoleServices {
    private static RoleServices INSTANCE = null;

    public static RoleServices getInstance() {
         if(INSTANCE == null) {
             INSTANCE = new RoleServices();
         }
         return INSTANCE;
    }
    private final RoleRepsository roleRepsository = new RoleRepsository();
    public List<RoleModel> getAllRole() {
         return roleRepsository.getAllRole();
    }
    public Integer deleteRoleById(String id) {
        return roleRepsository.deleteRole(id);
    }
    public Integer updateRoleById(RoleModel roleModel) {return roleRepsository.updateRole(roleModel);}
    public Integer createRole(RoleModel roleModel) {return roleRepsository.createRole(roleModel);}


}
