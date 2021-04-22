package entity;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Doctors")
public class Doctors extends SuperID {

    @Column(name = "Name")
    private String name;

    @Column(name = "Specialty")
    private String specialty;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "doctors")
    private List<Conclusion> conclusions;

}
