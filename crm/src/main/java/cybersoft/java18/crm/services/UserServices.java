package cybersoft.java18.crm.services;

import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.respository.UserRepository;

import java.util.List;

public class UserServices {
    private static UserServices INSTANCE = null;

    public static UserServices getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserServices();
        }
        return INSTANCE;
    }
    private final UserRepository userRepository = new UserRepository();

    public List<UserModel> getAllUser() {
        return userRepository.getAllUser();
    }
    public UserModel getUserById(String id) {return userRepository.getUserById(id);}
    public UserModel login(String email,String password) {
        UserModel userModel =  userRepository.getUserByEmail(email);
        if (userModel == null)
            return null;

        if (userModel.getPassword().equals(password))
            return userModel;

        return null;
    }
    public Integer deleteUserById(String id) {
        return userRepository.deleteUserById(id);
    }
    public Integer updateUser(UserModel userModel) {
        return userRepository.updateUser(userModel);
    }
    public Integer createUser(UserModel userModel) {
        return userRepository.createUser(userModel);
    }
}
