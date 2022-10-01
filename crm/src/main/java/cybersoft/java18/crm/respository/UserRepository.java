package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.model.StatusModel;
import cybersoft.java18.crm.model.UserModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserRepository extends AbstractRepository<UserModel> {
    public List<UserModel> getAllUser() {
        String query = "select * from users";
        List<UserModel> users = new ArrayList<>();

        return executeQuery(connection -> {
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                UserModel userModel = new UserModel();
                userModel.setId(resultSet.getInt("id"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                users.add(userModel);
            }
            return users;
        });
    }
    public UserModel getUserById(String id) {
        String query = "select * from users where id =?";
        return executeQuery2( connection -> {
            UserModel userModel = new UserModel();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setId(resultSet.getInt("id"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                return userModel;

            }
            return userModel;

        });
    }
    public UserModel getUserByEmail(String email) {
        String query = "select * from users where email=?";
        return executeQuery2( connection -> {
            UserModel userModel = new UserModel();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                userModel.setFullName(resultSet.getString("fullname"));
                userModel.setId(resultSet.getInt("id"));
                userModel.setAvatar(resultSet.getString("avatar"));
                userModel.setEmail(resultSet.getString("email"));
                userModel.setPassword(resultSet.getString("password"));
                userModel.setRoleId(resultSet.getInt("role_id"));
                return userModel;

            }
            return userModel;

        });
    }
    public Integer deleteUserById(String id) {
        String query = "delete from users where id = ? ";
       return executeSaveAndUpdate(connection -> {
           PreparedStatement preparedStatement = connection.prepareStatement(query);
           preparedStatement.setString(1,id);
           return preparedStatement.executeUpdate();
       });
    }
    public Integer updateUser(UserModel userModel) {
        String query = "update users set email = ?,password = ?,fullName = ?,avatar = ?,role_id = ? where id = ? ";
        return executeSaveAndUpdate(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userModel.getEmail());
            preparedStatement.setString(2, userModel.getPassword());
            preparedStatement.setString(3, userModel.getFullName());
            preparedStatement.setString(4, userModel.getAvatar());
            preparedStatement.setInt(5, userModel.getRoleId());
            preparedStatement.setInt(6, userModel.getId());
            return preparedStatement.executeUpdate();
        });
    }
    public Integer createUser(UserModel userModel) {
        return executeSaveAndUpdate(connection -> {
            String query = "insert into users(email,password,fullName,avatar,role_id) values(?,?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, userModel.getEmail());
            preparedStatement.setString(2, userModel.getPassword());
            preparedStatement.setString(3, userModel.getFullName());
            preparedStatement.setString(4, userModel.getAvatar());
            preparedStatement.setInt(5, userModel.getRoleId());
            return preparedStatement.executeUpdate();
        });
    }

}
