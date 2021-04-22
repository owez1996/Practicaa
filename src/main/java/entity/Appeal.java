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
@Table(name = "Appeal")
public class Appeal extends SuperID {

    @Column(name = "Complains")
    private String complains;

    @Column(name = "DateOfTheApplication")
    private String DateOfTheApplication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Patients")
    private Patients patients;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appeal")
    private List<Conclusion> conclusions;
}
