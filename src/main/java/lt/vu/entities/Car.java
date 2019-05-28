package lt.vu.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Car")
@NamedQueries({
        @NamedQuery(name = "Car.findAll", query = "SELECT c FROM Car c")})
@Getter
@Setter
public class Car implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "BRAND")
    private String brand;

    @Column(name = "MODEL")
    private String model;

    @Column(name = "NUMBER")
    private String number;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;
}
