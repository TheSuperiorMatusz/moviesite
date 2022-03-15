package pl.moviebase.moviesite.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@Table(name = "chairman")
public class Chairman implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "personal_id_num", nullable = false)
    private String personalIdNum;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    public Chairman(){

    }
    public Chairman(String personalIdNum,String name,String surname){
        this.personalIdNum=personalIdNum;
        this.name=name;
        this.surname=surname;
    }

    public Studio getStudio() {
        return studio;
    }

    public void setStudio(Studio studio) {
        this.studio = studio;
    }
    @JsonIgnore
    @OneToOne(mappedBy = "chairman",cascade = CascadeType.ALL)
    private Studio studio;
}
