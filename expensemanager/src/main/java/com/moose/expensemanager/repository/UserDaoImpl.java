package com.moose.expensemanager.repository;

import com.moose.expensemanager.entity.User;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class UserDaoImpl implements IUserDao{

    @Autowired
    private EntityManager entityManager;


    @Override
    public void save(User user) {

        Session currentSession = entityManager.unwrap(Session.class);

        currentSession.saveOrUpdate(user);
    }
}
