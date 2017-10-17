package ua.com.owu.dao;

import ua.com.owu.entity.seo.FakeUser;

import java.util.Collection;
import java.util.List;

public interface FakeUserDAO {
    void save(FakeUser fakeUser);

    List<FakeUser> findAll();

    FakeUser findById(String id);

}
