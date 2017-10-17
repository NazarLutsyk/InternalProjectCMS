package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.FakeAccountDAO;
import ua.com.owu.entity.seo.FakeAccount;
import ua.com.owu.service.FakeAccountService;

import java.util.Collection;
import java.util.List;

@Service
public class FakeAccountServiceImpl implements FakeAccountService {
    @Autowired
    FakeAccountDAO fakeAccountDAO;

    @Override
    public void save(FakeAccount fakeAccount) {
        fakeAccountDAO.save(fakeAccount);
    }

    @Override
    public List<FakeAccount> findAll() {
        return fakeAccountDAO.findAll();
    }

    @Override
    public FakeAccount findById(String id) {
        return fakeAccountDAO.findById(id);
    }

    @Override
    public void save(Collection<FakeAccount> accounts) {
        fakeAccountDAO.save(accounts);
    }

    @Override
    public List<FakeAccount> findAllWithoutUser() {
        return fakeAccountDAO.findAllWithoutUser();
    }
}
