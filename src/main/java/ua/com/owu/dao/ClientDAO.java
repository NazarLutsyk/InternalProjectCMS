package ua.com.owu.dao;

import ua.com.owu.entity.Client;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface ClientDAO {


    void save(Client client);

    List<Client> findAll();

    Set<Client> findAll(Set<String> idList);

    Client findOne(String id);

    void save(Collection<Client> clients);

    List<Client> findAllByGroupIdentifier(String groupIdentifier);

    List<Client> findClientsWitchNotFromGroupAndWithApp(String groupIdentifier);

    List<Client> findClientsWithoutGroups();

//    public List<Client> findAllByNameOrSurnameOrPhoneOrEmail(String condition);
}
