package ru.javaweb.tracker.web.patient;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javaweb.tracker.model.Patient;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class PatientServlet extends HttpServlet {
    private ConfigurableApplicationContext springContext;
    private PatientRestController patientRestController;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        springContext = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        patientRestController = springContext.getBean(PatientRestController.class);
    }

    @Override
    public void destroy() {
        springContext.close();
        super.destroy();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Patient patient = new Patient(req.getParameter("firstName"), req.getParameter("middleName"),
                req.getParameter("lastName"), Integer.valueOf(req.getParameter("insuranceId")), id.isEmpty() ? null : Integer.valueOf(id),
                1);
        if (req.getParameter("id").isEmpty())
            patientRestController.create(patient);
        else
            patientRestController.update(patient, patient.getId());

        resp.sendRedirect("patients");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                patientRestController.delete(id);
                response.sendRedirect("patients");
                break;
            case "create":
            case "update":
                final Patient patient = "create".equals(action) ?
                        new Patient(1):
                        patientRestController.get(getId(request));
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("/patientForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                request.setAttribute("patients", patientRestController.getAll());
                request.getRequestDispatcher("/patients.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return  Integer.valueOf(paramId);
    }
}
