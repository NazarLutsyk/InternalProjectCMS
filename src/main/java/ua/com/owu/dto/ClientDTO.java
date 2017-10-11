package ua.com.owu.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private String id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

}
