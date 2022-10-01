package cybersoft.java18.crm.api;

import com.google.gson.*;
import cybersoft.java18.crm.model.JobModel;
import cybersoft.java18.crm.model.ResponseData;
import cybersoft.java18.crm.model.RoleModel;
import cybersoft.java18.crm.services.JobServices;
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
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;

@WebServlet(name="job",urlPatterns = {
        UrlUtils.URL_JOB,
        UrlUtils.URL_JOB_BY_ID
})
public class JobController extends HttpServlet {
    private final Gson gson = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        @Override
        public LocalDateTime deserialize(JsonElement json, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
            return LocalDateTime.parse(json.getAsJsonPrimitive().getAsString(),formatter);
        }
    }).create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Objects.equals(req.getServletPath(), UrlUtils.URL_JOB)) {
            getAllJob(req, resp);
        }
        if (Objects.equals(req.getServletPath(),  UrlUtils.URL_JOB_BY_ID)) {
            getJobById(req, resp);
        }

    }

    private void getJobById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String id = req.getParameter("id");
        JobModel job = JobServices.getInstance().getJobById(id);

        String json = gson.toJson(job);


        PrintWriter printWriter = resp.getWriter();
        printWriter.println(json);
        printWriter.flush();
    }

    private void getAllJob(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<JobModel> jobs = JobServices.getInstance().getAllJob();

        String json = gson.toJson(jobs);


        PrintWriter printWriter = resp.getWriter();
        printWriter.println(json);
        printWriter.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id =  req.getParameter("id");
        Integer result  = JobServices.getInstance().deleteJobById(id);
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
        JobModel jobModel = gson.fromJson(data,JobModel.class);
        JobServices.getInstance().updateJobById(jobModel);
        System.out.println(data);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        String name = req.getParameter("name");

        LocalDateTime startDate = LocalDateTime.parse(req.getParameter("startDate"),formatter);
        LocalDateTime endDate = LocalDateTime.parse(req.getParameter("endDate"),formatter);
        JobModel jobModel = new JobModel();
        jobModel.setName(name);
        jobModel.setStartDate(startDate);
        jobModel.setEndDate(endDate);
        JobServices.getInstance().createJob(jobModel);
        System.out.println(jobModel);

    }
}
