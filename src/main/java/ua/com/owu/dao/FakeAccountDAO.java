package ua.com.owu.dao;

import ua.com.owu.entity.seo.FakeAccount;

import java.util.Collection;
import java.util.List;

public interface FakeAccountDAO {
    void save(FakeAccount fakeAccount);

    List<FakeAccount> findAll();

    FakeAccount findById(String id);

    void save(Collection<FakeAccount> accounts);

    List<FakeAccount> findAllWithoutUser();
}
