package ua.com.owu.entity.seo;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.format.annotation.DateTimeFormat;

import java.net.URI;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true, value = "fakeAccount")
@ToString(exclude = "fakeUser")
@EqualsAndHashCode(exclude = "fakeUser")
@Builder
public class FakeAccount {
    @Id
    private ObjectId id;
    private String login;
    private String password;
    private URI siteUri;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date registrationDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date lastVisitDate;
    @Reference
    private FakeUser fakeUser;
    private List<String> fakeAccountComments = new ArrayList<>();
}
