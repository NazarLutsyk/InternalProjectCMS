package ua.com.owu.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;

@Entity(noClassnameStored = true,value = "course")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = {"groups"})
public class Course {
    @Id
    private ObjectId id;
    @Indexed
    private String courseTitle;
    private Integer fullPrice;
//    @Reference
//    private Set<Group> groups = new HashSet<>();
//    private Integer lessonQuantity;

}
