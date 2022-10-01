package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.model.RoleModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleRepsository extends AbstractRepository<RoleModel>{


    public List<RoleModel> getAllRole() {
        //connection database
        //prepareStatement
        //executeQuery or executeUpdate
        final String query = "select * from roles";
        List<RoleModel> roles = new ArrayList<>();
       return executeQuery(connection -> {

           PreparedStatement statement = connection.prepareStatement(query);

           ResultSet results = statement.executeQuery();
          while (results.next()) {
              int id = results.getInt("id");
              String name = results.getString("name");
              String description = results.getString("description");

              RoleModel roleModel = new RoleModel(id,name,description);
              roles.add(roleModel);

          }
          return roles;
       });
    }
    public Integer deleteRole(String id) {
         return executeSaveAndUpdate(connection -> {
             String query = "delete from roles where id = ?";
             PreparedStatement preparedStatement = connection.prepareStatement(query);
              preparedStatement.setString(1,id);
              return preparedStatement.executeUpdate();
          });
    }
    public Integer updateRole(RoleModel roleModel) {
        return executeSaveAndUpdate(connection -> {
           String query = "update roles set name=?,description=? WHERE id=?";
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,roleModel.getName());
           preparedStatement.setString(2,roleModel.getDescription());
           preparedStatement.setInt(3,roleModel.getId());
           return preparedStatement.executeUpdate();
        });
    }
    public Integer createRole(RoleModel roleModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "insert into roles(name,description) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, roleModel.getName());
            preparedStatement.setString(2, roleModel.getDescription());
            return preparedStatement.executeUpdate();
        });
    }
}
