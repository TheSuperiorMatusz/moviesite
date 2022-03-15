package pl.moviebase.moviesite.model;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Data
@Entity
@Table(name = "studio")
public class Studio implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address")
    private String address;

    public Studio(){

    }

    public Studio(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Chairman getChairman() {
        return chairman;
    }

    public void setChairman(Chairman chairman) {
        this.chairman = chairman;
    }
    @OneToOne
    @JoinColumn(name="chairman_num",referencedColumnName = "personal_id_num")
    private Chairman chairman;
    @JsonIgnore
   @OneToMany(mappedBy = "studio")
    private Set<Films> films;
}
