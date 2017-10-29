package ua.com.owu.dao.impl;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.query.CountOptions;
import org.mongodb.morphia.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ua.com.owu.dao.ClientDAO;
import ua.com.owu.entity.Application;
import ua.com.owu.entity.Client;
import ua.com.owu.entity.Group;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private Datastore datastore;

    public void save(Client client) {
        datastore.save(client);
        System.out.println("client saved");
    }

    public List<Client> findAll() {
        List<Client> clientList = datastore.createQuery(Client.class).asList();
        System.out.println("found list : " + clientList);
        return clientList;
    }

    public Client findOne(String id) {
        Client client = datastore.get(Client.class, new ObjectId(id));
        System.out.println("found application : " + client);
        return client;
    }

    @Override
    public void save(Collection<Client> clients) {
        datastore.save(clients);
        System.out.println("Collection of clients saved");
    }

    @Override
    public List<Client> findAllByGroupIdentifier(String groupIdentifier) {
        Group group = datastore
                .find(Group.class)
                .project("clients", true)
                .field("groupIdentifier")
                .equal(groupIdentifier)
                .get();
        System.out.println("Found list of groups:" + group.getClients());
        return new ArrayList<Client>(group.getClients());
    }

    @Override
    public List<Client> findClientsWitchNotFromGroupAndWithApp(String groupId) {
        Query<Group> groupQuery = datastore.createQuery(Group.class)
                .field("id").equal(new ObjectId(groupId));
        Query<Application> applicationQuery = datastore.find(Application.class)
                .field("course").equal(groupQuery.get().getCourse())
                .filter("checked !=",true);

        Query<Client> query = datastore.createQuery(Client.class);
        query.and(
                query.criteria("groups").hasNoneOf(groupQuery.asList()),
                query.criteria("applications").hasAnyOf(applicationQuery.asList())
        );

        List<Client> clients = query.asList();

        System.out.println("Found list client witch not from group and with app:" + clients);
        return clients;
    }

    @Override
    public List<Client> findClientsWithoutGroups() {
        //todo create query client.app.size == client.groups.size
//        Query<Client> query = datastore.createQuery(Client.class);
//        System.out.println("Found list client witch not from group:" + clients);
        return new ArrayList<>();
    }

//    @Override
//    public List<Client> findAllByNameOrSurnameOrPhoneOrEmail(String condition) {
//        Query<Client> query = datastore.createQuery(Client.class);
//        CriteriaContainer or = query.or(
//                query.criteria("name").containsIgnoreCase(condition),
//                query.criteria("surname").containsIgnoreCase(condition),
//                query.criteria("phoneNumber").containsIgnoreCase(condition),
//                query.criteria("email").containsIgnoreCase(condition)
//
//        );
//
//
//        return query.asList();
//    }


    @Override
    public Set<Client> findAll(Set<String> idList) {
        Set<Client> clients = new HashSet<>();
        idList.forEach(id -> clients.add(findOne(id)));
        System.out.println("All clients:" + clients);
        return clients;
    }
}
