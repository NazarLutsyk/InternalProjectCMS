package ua.com.owu.service;

import ua.com.owu.entity.seo.FakeUser;

import java.util.Collection;
import java.util.List;

public interface FakeUserService {
    void save(FakeUser fakeUser);

    List<FakeUser> findAll();

    FakeUser findById(String id);

}
