package ua.com.owu.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Indexed;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.*;

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
    private String commnetFromClient;
    private String commentFromManager;
    @Indexed
    private Set<String> tagsAboutApplication = new HashSet<>();
    private String futureCourse;
    private String appCloseDate;
    private boolean checked = false;
    private Integer discount;
    private Double priceWithDiscount;
    private Double paid;
    private Double leftToPay;
    @Reference
    @JsonIgnore
    private Social source;
    @Reference
    @JsonIgnore
    private Client client;
    @Reference
    @JsonIgnore
    private Course course;
    @Reference
    @JsonIgnore
    private List<Payment> payments = new ArrayList<>();
}
