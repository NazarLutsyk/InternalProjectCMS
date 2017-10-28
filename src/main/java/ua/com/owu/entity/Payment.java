package ua.com.owu.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;
import org.mongodb.morphia.annotations.Reference;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(noClassnameStored = true,value = "payments")
@ToString(exclude = {"application"})
@EqualsAndHashCode(exclude = {"application"})
@Builder
public class Payment {
    @Id
    private ObjectId id;
    private Double amount;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date dateOfPayment;
    @Reference
    Application application;
}
