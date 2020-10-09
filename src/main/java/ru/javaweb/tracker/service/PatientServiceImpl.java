package ru.javaweb.tracker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.repository.PatientRepository;
import ru.javaweb.tracker.util.exception.NotFoundException;

import java.util.List;

import static ru.javaweb.tracker.util.ValidationUtil.checkNotFoundWithId;
@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;

    @Autowired
    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Patient create(Patient patient, int userId) {
        return repository.save(patient, userId);
    }

    @Override
    public void delete(int id, int userId) throws NotFoundException {
        repository.delete(id, userId);
    }

    @Override
    public Patient get(int id, int userId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, userId), id);
    }

    @Override
    public void update(Patient patient, int userId) {
        repository.save(patient, userId);
    }

    @Override
    public List<Patient> getAll(int userId) {
        return repository.getAll(userId);
    }
}
