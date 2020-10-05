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
    @Autowired
    private PatientRepository repository;

    public void setRepository(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Patient create(Patient patient) {
        return repository.save(patient);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        repository.delete(id);
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
        return repository.getAll();
    }
}
