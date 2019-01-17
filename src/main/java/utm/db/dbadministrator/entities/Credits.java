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
public class Credits {

    @Id
    @GeneratedValue(strategy = AUTO)
    private long id;
    private String creditName;
    private String creditNumber;
    private String getCreditCode;
}
