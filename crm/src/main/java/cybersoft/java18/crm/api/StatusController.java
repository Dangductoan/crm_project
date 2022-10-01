package cybersoft.java18.crm.api;

import com.google.gson.Gson;
import cybersoft.java18.crm.model.StatusModel;
import cybersoft.java18.crm.services.StatusService;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name="status" ,urlPatterns = {
       UrlUtils.URL_STATUS
})
public class StatusController extends HttpServlet {
    private final Gson gson = new Gson();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        StatusModel statusModel = StatusService.getInstance().getStatusById(id);

        String json = gson.toJson(statusModel);
        PrintWriter printWriter = resp.getWriter();

        printWriter.println(json);
        printWriter.flush();
    }
}
