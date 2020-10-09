package ru.javaweb.tracker.service;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.util.exception.NotFoundException;

import java.util.List;

public interface PatientService {
    Patient create(Patient patient, int userId);

    void delete(int id, int userId) throws NotFoundException;

    Patient get(int id, int userId) throws NotFoundException;

    void update(Patient user, int userId);

    List<Patient> getAll(int userId);
}
