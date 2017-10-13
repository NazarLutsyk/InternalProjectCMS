package ua.com.owu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.com.owu.dao.ClientDAO;
import ua.com.owu.entity.Client;
import ua.com.owu.service.ClientService;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Service
public class ClientServiceImpl implements ClientService {


    @Autowired
    private ClientDAO clientDAO;

    @Override
    public void save(Client client) {
        clientDAO.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientDAO.findAll();
    }

    @Override
    public Client findOne(String id) {
        return clientDAO.findOne(id);
    }

    @Override
    public Set<Client> findAll(Set<String> idList) {
        return clientDAO.findAll(idList);
    }

    @Override
    public void save(Collection<Client> clients) {
        clientDAO.save(clients);
    }

    @Override
    public List<Client> findAllByGroupIdentifier(String groupIdentifier) {
        return clientDAO.findAllByGroupIdentifier(groupIdentifier);
    }

    @Override
    public List<Client> findClientsWitchNotFromGroupAndWithApp(String groupIdentifier) {
        return clientDAO.findClientsWitchNotFromGroupAndWithApp(groupIdentifier);
    }

    @Override
    public List<Client> findClientsWithoutGroups() {
        return clientDAO.findClientsWithoutGroups();
    }

//    @Override
//    public List<Client> findAllByNameOrSurnameOrPhoneOrEmail(String condition) {
//
//        return clientDAO.findAllByNameOrSurnameOrPhoneOrEmail(condition);
//    }
}
