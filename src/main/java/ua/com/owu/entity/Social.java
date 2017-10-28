package ua.com.owu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true, value = "social")
@ToString(exclude = {"applications"})
@EqualsAndHashCode(exclude = {"applications"})
@Builder
public class Social {
    @Id
    private ObjectId id;
    private String name;
    @Reference
    @JsonIgnore
    List<Application> applications = new ArrayList<>();
}
