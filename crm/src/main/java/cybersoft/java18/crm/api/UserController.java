package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.UserModel;
import cybersoft.java18.crm.services.UserServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Objects;

@WebServlet(name="user",urlPatterns = {
        UrlUtils.URL_USER,
        UrlUtils.URL_USER_BY_ID,
        UrlUtils.URL_USER_LOGIN
})
public class UserController extends HttpServlet {
    final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(req.getServletPath(), UrlUtils.URL_USER)) {
            getAllUser(req, resp);

        }
        if (Objects.equals(req.getServletPath(),  UrlUtils.URL_USER_BY_ID)) {
            getUserById(req, resp);


        }

    }

    private void getUserById(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        String id = req.getParameter("id");
        UserModel user = UserServices.getInstance().getUserById(id);
        String json = gson.toJson(user);

        PrintWriter printWriter = resp.getWriter();

        printWriter.println(json);
        printWriter.flush();
    }

    private void getAllUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<UserModel> users = UserServices.getInstance().getAllUser();
        String json = gson.toJson(users);

        PrintWriter printWriter = resp.getWriter();

        printWriter.println(json);
        printWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Integer result = UserServices.getInstance().deleteUserById(id);
        ResponseData responseData = new ResponseData();
        if(result == 1) {
            responseData.setSuccess(true);
            responseData.setMessage("Xóa thành công");
            responseData.setStatusCode(200);
        }else {
            responseData.setSuccess(false);
            responseData.setMessage("Xóa thất bại");
            responseData.setStatusCode(200);
        }

        PrintWriter printWriter = resp.getWriter();
        printWriter.println(gson.toJson(responseData));
        printWriter.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader br = new BufferedReader(req.getReader());
        StringBuilder builder = new StringBuilder();
        String line;
        while((line = br.readLine()) != null) {
            builder.append(line);
        }
        String data = builder.toString();
        UserModel userModel = gson.fromJson(data,UserModel.class);
        UserServices.getInstance().updateUser(userModel);
        System.out.println(userModel);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (Objects.equals(req.getServletPath(), UrlUtils.URL_USER)) {
            createUser(req, resp);

        }
        if (Objects.equals(req.getServletPath(),  UrlUtils.URL_USER_LOGIN)) {
            processLogin(req, resp);


        }


    }

    private void processLogin(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        var usermodel = UserServices.getInstance().login(req.getParameter("email"), req.getParameter("password"));

        if (usermodel == null) {
            req.setAttribute("errors", "email or password is incorrect!");

        } else {
            req.getSession().setAttribute("currentUser", usermodel);
        }


    }

    private void createUser(HttpServletRequest req, HttpServletResponse resp) {
        String fullName = req.getParameter("fullName");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String avatar = req.getParameter("avatar");
        String roleId = req.getParameter("roleId");
        UserModel userModel = new UserModel();
        userModel.setAvatar(avatar);
        userModel.setPassword(password);
        userModel.setFullName(fullName);
        userModel.setEmail(email);
        userModel.setRoleId(Integer.parseInt(roleId));
        UserServices.getInstance().createUser(userModel);
        System.out.println(userModel);
    }
}
