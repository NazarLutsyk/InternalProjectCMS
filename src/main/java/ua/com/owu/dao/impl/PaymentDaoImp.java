package ua.com.owu.dao.impl;

import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.PaymentDao;
import ua.com.owu.entity.Payment;

@Repository
public class PaymentDaoImp implements PaymentDao {
    @Autowired
    Datastore datastore;

    @Override
    public void save(Payment payment) {
        datastore.save(payment);
        System.out.println("paymant saved");
    }
}
