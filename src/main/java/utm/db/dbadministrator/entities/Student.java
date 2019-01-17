package utm.db.dbadministrator.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Data
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String studentRegistrationNumber;
    private String studentYear;
    private String studentGroup;
    private String phone;

}
