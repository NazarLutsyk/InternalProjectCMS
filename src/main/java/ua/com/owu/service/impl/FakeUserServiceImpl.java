package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.FakeUserDAO;
import ua.com.owu.entity.seo.FakeUser;
import ua.com.owu.service.FakeUserService;

import java.util.Collection;
import java.util.List;

@Service
public class FakeUserServiceImpl implements FakeUserService {
    @Autowired
    FakeUserDAO fakeUserDAO;

    @Override
    public void save(FakeUser fakeUser) {
        fakeUserDAO.save(fakeUser);
    }

    @Override
    public List<FakeUser> findAll() {
        return fakeUserDAO.findAll();
    }

    @Override
    public FakeUser findById(String id) {
        return fakeUserDAO.findById(id);
    }

}
