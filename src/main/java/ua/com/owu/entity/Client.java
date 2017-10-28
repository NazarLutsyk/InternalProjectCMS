package ua.com.owu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.jsonschema.JsonSerializableSchema;
import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true, value = "client")
@ToString(exclude = {"groups","applications"})
@EqualsAndHashCode(exclude = {"groups", "recomendation","applications"})
@Builder
public class Client {
    @Id
    private ObjectId id;
    @Indexed
    private String name;
    @Indexed
    private String surname;
    @Indexed
    private String phoneNumber;
    @Indexed
    private String email;
    private String city;
    @Reference
    @JsonIgnore
    private List<Comment> commentsAboutClient = new ArrayList<>();
    private Set<String> tagsAboutClient = new HashSet<>();
    //    private Map<String, String> socials = new HashMap<>();
    @JsonIgnore
    @Reference
    private Client recomendation;
    @JsonIgnore
    @Reference
    private Set<Group> groups = new HashSet<>();
    @JsonIgnore
    @Reference
    private List<Application> applications = new ArrayList<>();

}
