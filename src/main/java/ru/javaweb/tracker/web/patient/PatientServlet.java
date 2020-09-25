package ru.javaweb.tracker.web.patient;

import org.slf4j.Logger;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.repository.InMemoryPatientRepositoryImpl;
import ru.javaweb.tracker.repository.PersonRepository;
import ru.javaweb.tracker.web.user.UserServlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class PatientServlet extends HttpServlet {
    private PersonRepository<Patient> repository;
    private static final Logger log = getLogger(UserServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryPatientRepositoryImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Patient patient = new Patient(req.getParameter("firstName"), req.getParameter("middleName"),
                req.getParameter("lastName"), Integer.valueOf(req.getParameter("insuranceId")), id.isEmpty() ? null : Integer.valueOf(id)
                );
        log.info(patient.isNew() ? "Create patient{}" : "Update patient{}", patient);
        repository.save(patient);
        resp.sendRedirect("patients");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        switch (action == null ? "all" : action) {
            case "delete":
                int id = getId(request);
                log.info("Delete patient {}", id);
                repository.delete(id);
                response.sendRedirect("patients");
                break;
            case "create":
            case "update":
                final Patient patient = "create".equals(action) ?
                        new Patient():
                        repository.get(getId(request));
                request.setAttribute("patient", patient);
                request.getRequestDispatcher("/patientForm.jsp").forward(request, response);
                break;
            case "all":
            default:
                log.info("getAllPatients");
                request.setAttribute("patients", repository.getAll());
                request.getRequestDispatcher("/patients.jsp").forward(request, response);
        }
    }

    private int getId(HttpServletRequest request) {
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return  Integer.valueOf(paramId);
    }
}
