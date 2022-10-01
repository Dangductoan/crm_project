package cybersoft.java18.crm.api;

import com.google.gson.*;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.TaskModel;
import cybersoft.java18.crm.services.TaskServices;
import cybersoft.java18.crm.utils.UrlUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name="task",urlPatterns = {
        UrlUtils.URL_TASK,

})
public class TaskController extends HttpServlet {

    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),formatter);
        }
    }).create();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TaskModel> tasks = TaskServices.getInstance().getAllTask();
        String json = gson.toJson(tasks);

        PrintWriter printWriter = resp.getWriter();

        printWriter.println(json);
        printWriter.flush();
    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("id");
        Integer result  = TaskServices.getInstance().deleteTaskById(id);
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
        TaskModel taskModel  = gson.fromJson(data,TaskModel.class);
        TaskServices.getInstance().updateTaskById(taskModel);
        System.out.println(taskModel);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String name = req.getParameter("name");
        String jobId = req.getParameter("jobId");
        String userId = req.getParameter("userId");
        String statusId = req.getParameter("statusId");
        LocalDateTime startDate = LocalDateTime.parse(req.getParameter("startDate"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(req.getParameter("endDate"),formatter);
        TaskModel taskModel = new TaskModel();
        taskModel.setJobId(Integer.parseInt(jobId));
        taskModel.setUserId(Integer.parseInt(userId));
        taskModel.setStatusId(Integer.parseInt(statusId));
        taskModel.setName(name);
        taskModel.setStartDate(startDate);
        taskModel.setEndDate(endDate);
        TaskServices.getInstance().createTask(taskModel);
        System.out.println(taskModel);

    }
}
