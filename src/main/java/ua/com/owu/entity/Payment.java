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
@Entity(noClassnameStored = true,value = "payment")
@ToString(exclude = {"application"})
@EqualsAndHashCode(exclude = {"application"})
@Builder
public class Payment {
    @Id
    private ObjectId id;
    private Integer discount;
    private Double priceWithDiscount;
    private Double paid;
    private Double leftToPay;
    @Reference
    Application application;
}
