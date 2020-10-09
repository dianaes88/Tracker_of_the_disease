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

import static ru.javaweb.tracker.repository.mock.InMemoryUserRepositoryImpl.ADMIN_ID;
import static ru.javaweb.tracker.repository.mock.InMemoryUserRepositoryImpl.USER_ID;

@Repository
public class InMemoryPatientRepositoryImpl implements PatientRepository {
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
