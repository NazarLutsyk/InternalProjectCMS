package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.PaymentDao;
import ua.com.owu.entity.Payment;
import ua.com.owu.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    PaymentDao paymentDao;

    @Override
    public void save(Payment payment) {
        paymentDao.save(payment);
    }
}
