package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.respository.RoleRepsository;
import cybersoft.java18.crm.services.RoleServices;
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

@WebServlet(name="role" ,urlPatterns = {
        UrlUtils.URL_ROLE,

})
public class RoleController extends HttpServlet {
    private final Gson  gson = new Gson();



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<RoleModel> roles = RoleServices.getInstance().getAllRole();

        String json = gson.toJson(roles);
        PrintWriter printWriter = resp.getWriter();

        printWriter.println(json);
        printWriter.flush();
    }



    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       String id =  req.getParameter("id");
       Integer result  = RoleServices.getInstance().deleteRoleById(id);
        ResponseData responseData = new ResponseData();
       if(result == 1 ){
           responseData.setStatusCode(200);
           responseData.setMessage("Xóa thành công");
           responseData.setSuccess(true);
       }else {
           responseData.setStatusCode(200);
           responseData.setMessage("Xóa thất bại");
           responseData.setSuccess(false);
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
        RoleModel roleModel = gson.fromJson(data,RoleModel.class);
        RoleServices.getInstance().updateRoleById(roleModel);
        System.out.println(roleModel);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       String name = req.getParameter("name");
       String description = req.getParameter("description");

       RoleModel roleModel = new RoleModel();
       roleModel.setName(name);
       roleModel.setDescription(description);
       RoleServices.getInstance().createRole(roleModel);
       System.out.println(roleModel);

    }
}
