package ua.com.owu.service.util;

import org.springframework.stereotype.Component;
import ua.com.owu.dto.ClientDTO;
import ua.com.owu.entity.Client;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClientDTOAdapter {

    public ClientDTO convertToClientDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId().toHexString())
                .name(client.getName())
                .surname(client.getSurname())
                .phoneNumber(client.getPhoneNumber())
                .email(client.getEmail())
                .build();

    }
    public List<ClientDTO> convertToListOfClientDTO(List<Client> clients ) {
        List<ClientDTO> clientDTOS = new ArrayList<>();
        clients.forEach(client -> clientDTOS.add(convertToClientDTO(client)));
        return clientDTOS;
    }


}
