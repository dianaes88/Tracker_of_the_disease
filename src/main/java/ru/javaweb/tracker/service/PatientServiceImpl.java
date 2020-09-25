package ru.javaweb.tracker.service;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.repository.PatientRepository;
import ru.javaweb.tracker.repository.PersonRepository;
import ru.javaweb.tracker.util.exception.NotFoundException;

import java.util.List;

import static ru.javaweb.tracker.util.ValidationUtil.checkNotFoundWithId;

public class PatientServiceImpl implements PatientService {
    private PatientRepository repository;

    @Override
    public Patient create(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Patient get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public void update(Patient patient) {
        repository.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return (List<Patient>) repository.getAll();
    }
}
