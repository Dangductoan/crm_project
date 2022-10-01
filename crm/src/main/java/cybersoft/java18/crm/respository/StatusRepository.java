package cybersoft.java18.crm.respository;

import cybersoft.java18.crm.model.StatusModel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatusRepository extends AbstractRepository<StatusModel>{
    public StatusModel getStatusById(String id) {
        String query = "select * from status where id =?";
        return executeQuery2( connection -> {
            StatusModel statusModel = new StatusModel();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {

                statusModel.setName(resultSet.getString("name"));
                statusModel.setId(resultSet.getInt("id"));
                return statusModel;

            }
            return statusModel;

        });
    }
}
