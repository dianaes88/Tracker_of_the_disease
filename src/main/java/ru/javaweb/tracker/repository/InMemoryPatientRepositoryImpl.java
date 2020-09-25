package ru.javaweb.tracker.repository;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.util.PatientsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryPatientRepositoryImpl implements PersonRepository<Patient>{
    private Map<Integer, Patient> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        PatientsUtil.PATIENTS.forEach(this::save);
    }

    @Override
    public Patient save(Patient patient) {
        if (patient.isNew()) {
            patient.setId(counter.incrementAndGet());
        }
        repository.put(patient.getId(), patient);
        return patient;
    }

    @Override
    public void delete(int id) {
        repository.remove(id);
    }

    @Override
    public Patient get(int id) {
        return repository.get(id);
    }

    @Override
    public Collection<Patient> getAll() {
        return repository.values();
    }
}
