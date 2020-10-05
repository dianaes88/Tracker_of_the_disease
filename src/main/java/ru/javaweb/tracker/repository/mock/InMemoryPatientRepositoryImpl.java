package ru.javaweb.tracker.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.repository.PatientRepository;
import ru.javaweb.tracker.util.PatientsUtil;
import ru.javaweb.tracker.web.AuthorizedUser;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryPatientRepositoryImpl implements PatientRepository {
    private Map<Integer, Patient> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        PatientsUtil.PATIENTS.forEach(this::save);
    }

    @Override
    public Patient save(Patient patient) {
        if (patient.getUserID() == AuthorizedUser.id()) {
            if (patient.isNew()) {
                patient.setId(counter.incrementAndGet());
            }
            repository.put(patient.getId(), patient);
        }
        return patient;
    }

    @Override
    public void delete(int id) {
        Patient p = repository.get(id);
        if (p.getUserID() == AuthorizedUser.id()) {
            repository.remove(id);
        }
    }

    @Override
    public Patient get(int id) {
        Patient p = repository.get(id);
        if (p.getUserID() == AuthorizedUser.id()) {
            return p;
        }
        return null;
    }

    @Override
    public List<Patient> getAll() {
        return PatientsUtil.getPatientsByName(repository.values());
    }
}
