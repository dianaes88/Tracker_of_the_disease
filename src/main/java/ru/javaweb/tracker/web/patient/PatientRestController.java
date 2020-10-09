package ru.javaweb.tracker.web.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.service.PatientService;
import ru.javaweb.tracker.web.AuthorizedUser;

import java.util.List;

import static ru.javaweb.tracker.util.ValidationUtil.assureIdConsistent;
import static ru.javaweb.tracker.util.ValidationUtil.checkNew;

@Controller
public class PatientRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final PatientService service;

    @Autowired
    public PatientRestController(PatientService service) {
        this.service = service;
    }

    public List<Patient> getAll() {
        int userId = AuthorizedUser.id();
        log.info("getAll patients for user {}", userId);
        return service.getAll(userId);
    }

    public Patient get(int id) {
        int userId = AuthorizedUser.id();
        log.info("get patient {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public Patient create(Patient patient) {
        int userId = AuthorizedUser.id();
        log.info("create {} for user {}", patient, userId);
        checkNew(patient);
        return service.create(patient, userId);
    }

    public void delete(int id) {
        int userId = AuthorizedUser.id();
        log.info("delete {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public void update(Patient patient, int id) {
        int userId = AuthorizedUser.id();
        log.info("update {} for user {}", patient, userId);
        assureIdConsistent(patient, id);
        service.update(patient, userId);
    }
}

