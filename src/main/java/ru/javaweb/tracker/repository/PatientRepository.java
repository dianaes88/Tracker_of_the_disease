package ru.javaweb.tracker.repository;

import ru.javaweb.tracker.model.Patient;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient user, int userId);

    // false if not found
    boolean delete(int id, int userId);

    // null if not found
    Patient get(int id, int userId);

    List<Patient> getAll(int userId);
}
