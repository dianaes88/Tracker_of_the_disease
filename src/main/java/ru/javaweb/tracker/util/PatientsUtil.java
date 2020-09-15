package ru.javaweb.tracker.util;

import ru.javaweb.tracker.model.Patient;
import ru.javaweb.tracker.model.User;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class PatientsUtil {
    public static final List<Patient> PATIENTS = Arrays.asList(
            new Patient("Сванте", null, "Свантесон", 12345),
            new Patient("Пеппи", "Длинный", "Чулок", 678),
            new Patient("Буратино", "Деревянное", "Полено", 888),
            new Patient("Девочка", "Красная", "Шапочка", 77777)

    );

    public static List<Patient> getPatientsByName(List<Patient> patients) {
        return patients.stream().sorted((u1, u2)->u1.getFirstName().compareTo(u2.getFirstName())).collect(Collectors.toList());
    }

    public static List<Patient> getPatientsBySurname(List<Patient> patients) {
        return patients.stream().sorted((u1, u2)->u1.getLastName().compareTo(u2.getLastName())).collect(Collectors.toList());
    }

    public static List<Patient> getPatientsByInsuranceID(List<Patient> patients) {
        return patients.stream().sorted((u1, u2)->u1.getInsuranceId().compareTo(u2.getInsuranceId())).collect(Collectors.toList());
    }
}
