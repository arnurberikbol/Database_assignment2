package com.arnur.database_assignment2.dao;

import com.arnur.database_assignment2.entity.Disease;
import com.arnur.database_assignment2.entity.Doctor;
import com.arnur.database_assignment2.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDao {

    @Autowired
    private EntityManager entityManager;

    @Transactional
    public List<User> getAllUsers() {

        Query query = entityManager.createQuery("from User");
        List<User> allUsers = query.getResultList();
        return allUsers;
    }

    @Transactional
    public boolean saveUser(User user) {
        try {
            entityManager.merge(user);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public boolean persistUser(User user) {
        String q = "INSERT INTO users VALUES (?,?,?,?,?,?)";
        System.out.println(q);

        Query query = entityManager.createQuery("from User");
        List<User> allUsers = query.getResultList();
        for (User user2 : allUsers) {
            if (user2.getEmail().equals(user.getEmail())) return false;
        }

        try {
            entityManager.createNativeQuery(q)
                    .setParameter(1,user.getEmail())
                    .setParameter(2,user.getName())
                    .setParameter(3,user.getSurname())
                    .setParameter(4,user.getSalary())
                    .setParameter(5,user.getPhone())
                    .setParameter(6,user.getCname())
                    .executeUpdate();
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Transactional
    public User getUser(String email) {
        User user = entityManager.find(User.class,email);
        return user;
    }

    @Transactional
    public void deleteUser(String email) {
        String q = "delete from users where email = ?";
        System.out.println(q);

        entityManager.createNativeQuery(q)
                .setParameter(1,email)
                .executeUpdate();

    }
}
