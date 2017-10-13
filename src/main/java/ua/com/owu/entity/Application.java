package ua.com.owu.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.format.annotation.DateTimeFormat;
import ua.com.owu.entity.enums.Social;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(noClassnameStored = true,value = "application")
public class Application {
    @Id
    private ObjectId id;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date appReciveDate;
    private Social source;
    private String commnetFromClient;
    private String commentFromManager;
    @Indexed
    private Set<String> tagsAboutApplication = new HashSet<>();
    private String futureCourse;
    private String appCloseDate;
    private boolean checked = false;
    @Reference
    private Client client;
    @Reference
    private Course course;
    @Reference
    private Payment payment;
}
