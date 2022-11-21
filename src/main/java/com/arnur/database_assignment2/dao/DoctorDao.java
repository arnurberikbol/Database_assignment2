package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Diseasetype;
import com.arnur.database_assignment2.entity.Doctor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;
@Repository
public class DoctorDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<Doctor> getAllDoctors() {

        Query query = entityManager.createQuery("from Doctor");
        List<Doctor> allDoctors = query.getResultList();
        return allDoctors;
    }

    @Transactional
    public boolean saveDoctor(Doctor doctor) {
        try {
            entityManager.merge(doctor);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistDoctor(Doctor doctor) {
        String q = "INSERT INTO Doctor VALUES (?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from Doctor");
        List<Doctor> allDoctors = query.getResultList();
        for (Doctor doctor1 : allDoctors) {
            if (doctor.getEmail().equals(doctor1.getEmail()))  return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,doctor.getEmail())
                    .setParameter(2,doctor.getDegree())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public Doctor getDoctor(String email) {
        Doctor doctor = entityManager.find(Doctor.class,email);
        return doctor;
    }

    @Transactional
    public void deleteDoctor(String email) {
        Query query = entityManager.createQuery("delete from Doctor " +
                "where email =:Email");
        query.setParameter("Email",email);
        query.executeUpdate();
    }
}
