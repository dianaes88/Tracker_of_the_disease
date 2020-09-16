package ru.javaweb.tracker.model;

import java.util.Objects;

public class Patient extends Person {
    private Integer insuranceId;

    public Patient(String firstName, String middleName, String lastName, Integer insuranceId) {
        this(firstName, middleName, lastName, insuranceId, null);
    }

    public Patient(String firstName, String middleName, String lastName, Integer insuranceId,Integer id) {
        super(firstName, middleName, lastName, id);
        this.insuranceId = insuranceId;
    }

    public Patient() {
        super();
    }

    public Integer getInsuranceId() {
        return insuranceId;
    }

    public void setInsuranceId(Integer insuranceId) {
        this.insuranceId = insuranceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;
        if (!super.equals(o)) return false;
        Patient patient = (Patient) o;
        return Objects.equals(insuranceId, patient.insuranceId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), insuranceId);
    }

    @Override
    public String toString() {
        return "Patient{" +
                super.toString() +
                "insuranceId=" + insuranceId +
                '}';
    }
}
