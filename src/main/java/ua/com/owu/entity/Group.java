package ua.com.owu.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity(noClassnameStored = true, value = "group")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"clients" ,"course"})
@EqualsAndHashCode(exclude = {"clients","course"})
@Builder
public class Group {
    @Id
    private ObjectId id;
    @Indexed
    private String groupIdentifier;
    private String room;
    private Date startDate;
    @Reference
    private Set<Client> clients = new HashSet<>();

    @Reference
    private Course course;

}


