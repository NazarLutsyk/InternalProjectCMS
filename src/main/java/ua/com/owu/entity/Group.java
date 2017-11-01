package ua.com.owu.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.*;
import org.mongodb.morphia.converters.DateConverter;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.format.annotation.DateTimeFormat;

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
    @JsonIgnore
    private Set<Client> clients = new HashSet<>();

    @Reference
    @JsonIgnore
    private Course course;

}


