package ru.javaweb.tracker.service;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.util.exception.NotFoundException;

import java.util.List;

public interface PatientService {
    Patient create(Patient patient);

    void delete(int id) throws NotFoundException;

    Patient get(int id) throws NotFoundException;

    void update(Patient user);

    List<Patient> getAll();
}
