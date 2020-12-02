package ru.javaweb.tracker.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.repository.PatientRepository;
import ru.javaweb.tracker.util.PatientsUtil;
import ru.javaweb.tracker.web.AuthorizedUser;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static ru.javaweb.tracker.UserTestData.ADMIN_ID;
import static ru.javaweb.tracker.UserTestData.USER_ID;

@Repository
public class InMemoryPatientRepositoryImpl implements PatientRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);
    private Map<Integer, Map <Integer,Patient>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        PatientsUtil.PATIENTS.forEach(meal->save(meal, USER_ID));
        save(new Patient("Иван", "Крестьянский", "Сын", 11222, ADMIN_ID), ADMIN_ID);
    }

    @Override
    public Patient save(Patient patient, int userId) {
        Map<Integer, Patient> patients = repository.computeIfAbsent(userId, ConcurrentHashMap::new);
        if (patient.isNew()) {
            patient.setId(counter.incrementAndGet());
            patients.put(patient.getId(), patient);
            return patient;
        }
        return patients.computeIfPresent(patient.getId(), (id, oldMeal) -> patient);
    }

    @PostConstruct
    public void postConstruct() {
        log.info("+++ PostConstruct");
    }

    @PreDestroy
    public void preDestroy() {
        log.info("+++ PreDestroy");
    }

    @Override
    public boolean delete(int id, int userId) {
        Map<Integer, Patient> patients = repository.get(userId);
        return patients != null && patients.remove(id) != null;
    }

    @Override
    public Patient get(int id, int userId) {
        Map<Integer, Patient> patients = repository.get(userId);
        return patients == null ? null : patients.get(id);
    }

    @Override
    public List<Patient> getAll(int userId) {
        Map<Integer, Patient> patients = repository.get(userId);
        return PatientsUtil.getPatientsByName(patients.values());
    }
}
