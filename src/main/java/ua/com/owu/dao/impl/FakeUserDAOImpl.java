package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.FakeUserDAO;
import ua.com.owu.entity.seo.FakeUser;

import java.util.Collection;
import java.util.List;

@Repository
public class FakeUserDAOImpl implements FakeUserDAO {
    @Autowired
    Datastore datastore;

    @Override
    public void save(FakeUser fakeUser) {
        datastore.save(fakeUser);
        System.out.println("FakeUser saved");
    }

    @Override
    public List<FakeUser> findAll() {
        List<FakeUser> fakeUsers = datastore.find(FakeUser.class).asList();
        System.out.println("Found list of fakeUsers:" + fakeUsers);
        return fakeUsers;
    }

    @Override
    public FakeUser findById(String id) {
        FakeUser fakeUser = datastore.get(FakeUser.class,new ObjectId(id));
        System.out.println("Found fakeUser:" + fakeUser);
        return fakeUser;
    }
}
