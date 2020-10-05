package ru.javaweb.tracker.repository;

import ru.javaweb.tracker.model.Patient;

import java.util.List;

public interface PatientRepository {
    Patient save(Patient user);

    // false if not found
    void delete(int id);

    // null if not found
    Patient get(int id);

    List<Patient> getAll();
}
