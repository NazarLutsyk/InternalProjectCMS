package ua.com.owu.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true, value = "comment")
@ToString(exclude = {"client"})
@EqualsAndHashCode(exclude = {"client"})
@Builder
public class Comment {
    @Id
    private ObjectId id;
    private String text;
    @Reference
    private Client client;
}
