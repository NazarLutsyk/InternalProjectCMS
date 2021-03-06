package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.FakeAccountDAO;
import ua.com.owu.entity.seo.FakeAccount;

import java.util.*;

@Repository
public class FakeAccountDAOImpl implements FakeAccountDAO {
    @Autowired
    Datastore datastore;

    @Override
    public void save(FakeAccount fakeAccount) {
        datastore.save(fakeAccount);
        System.out.println("FakeAccount saved");
    }

    @Override
    public List<FakeAccount> findAll() {
        List<FakeAccount> fakeAccounts = datastore.find(FakeAccount.class).asList();
        System.out.println("Found list of fakeAccounts:" + fakeAccounts);
        return fakeAccounts;
    }

    @Override
    public FakeAccount findById(String id) {
        FakeAccount fakeAccount = datastore.get(FakeAccount.class, new ObjectId(id));
        System.out.println("Found fakeAccount:" + fakeAccount);
        return fakeAccount;
    }

    @Override
    public void save(Collection<FakeAccount> accounts) {
        datastore.save(accounts);
        System.out.println("Collection of fakeAccounts saved");
    }

    @Override
    public List<FakeAccount> findAllWithoutUser() {
        Query<FakeAccount> query = datastore.createQuery(FakeAccount.class);
        query.or(
                query.criteria("fakeUser").doesNotExist(),
                query.criteria("fakeUser").sizeEq(0)
        );
        List<FakeAccount> accounts = query.asList();
        System.out.println("Found accounts without user:" + accounts);
        return accounts;
    }

    @Override
    public List<FakeAccount> findAllByIds(Set<String> ids) {
        List<FakeAccount> clients = new ArrayList<>();
        ids.forEach(id -> clients.add(findById(id)));
        System.out.println("All accounts by ids:" + clients);
        return clients;
    }

    @Override
    public void delete(String id) {
        datastore.delete(FakeAccount.class, new ObjectId(id));
        System.out.println("Fake account deleted");
    }
}
