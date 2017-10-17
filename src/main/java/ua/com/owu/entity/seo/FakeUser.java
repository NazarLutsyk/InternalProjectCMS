package ua.com.owu.entity.seo;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true, value = "fakeUser")
@ToString(exclude = "fakeAccounts")
@EqualsAndHashCode(exclude = "fakeAccounts")
@Builder
public class FakeUser {
    @Id
    private ObjectId id;
    private String name;
    private String surname;
    private String phone;
    private String email;
    private List<String> images = new ArrayList<>();
    private List<String> fakeUserComments = new ArrayList<>();
    @Reference
    private List<FakeAccount> fakeAccounts = new ArrayList<>();
}
