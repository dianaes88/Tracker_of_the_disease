package ru.javaweb.tracker.web.patient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.model.User;
import ru.javaweb.tracker.service.PatientService;

import java.util.List;

import static ru.javaweb.tracker.util.ValidationUtil.assureIdConsistent;
import static ru.javaweb.tracker.util.ValidationUtil.checkNew;

@Controller
public class PatientRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private PatientService service;

    @Autowired
    public PatientRestController(PatientService service) {
        this.service = service;
    }

    public List<Patient> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public Patient get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public Patient create(Patient patient) {
        log.info("create {}", patient);
        checkNew(patient);
        return service.create(patient);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public void update(Patient patient, int id) {
        log.info("update {} with id={}", patient, id);
        assureIdConsistent(patient, id);
        service.update(patient);
    }
}

