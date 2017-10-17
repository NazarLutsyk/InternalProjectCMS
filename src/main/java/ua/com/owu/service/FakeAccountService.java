package ua.com.owu.service;

import ua.com.owu.entity.seo.FakeAccount;

import java.util.Collection;
import java.util.List;

public interface FakeAccountService {
    void save(FakeAccount fakeAccount);

    List<FakeAccount> findAll();

    FakeAccount findById(String id);

    void save(Collection<FakeAccount> accounts);

    List<FakeAccount> findAllWithoutUser();
}
