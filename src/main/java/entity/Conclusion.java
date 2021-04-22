package entity;

import lombok.*;
import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "Conclusion")
public class Conclusion extends SuperID {

    @Column(name = "Diagnosis")
    private String diagnosis;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Doctor")
    private Doctors doctors;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_Appeal")
    private Appeal appeal;

    @Column(name = "Cost")
    private float cost;
}
