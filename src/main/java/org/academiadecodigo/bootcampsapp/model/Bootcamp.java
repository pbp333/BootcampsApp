package org.academiadecodigo.bootcampsapp.model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 10/11/17.
 */
@Entity
@Table(name = "bootcamps")
public class Bootcamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "bootcamp",
            fetch = FetchType.EAGER
    )
    private List<CodeCadet> codeCadets;
    private String city;
    private Date start;
    private Date end;

    public Bootcamp() {
    }

    public void addCadet(CodeCadet codeCadet) {

        codeCadets.add(codeCadet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<CodeCadet> getCodeCadets() {
        return codeCadets;
    }

    public void setCodeCadets(List<CodeCadet> codeCadets) {
        this.codeCadets = codeCadets;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public void setupBootcamp(String city, Date start, Date end) {

        this.city = city;
        this.start = start;
        this.end = end;
        codeCadets = new LinkedList<>();
    }

    public boolean equals(Bootcamp bootcamp) {

        return this.city.equals(bootcamp.getCity()) &&
                this.start.equals(bootcamp.getStart()) &&
                this.end.equals(bootcamp.getEnd());
    }


    @Override
    public String toString() {
        return "Bootcamp{" +
                "id=" + id +
                ", codeCadets=" + codeCadets +
                ", city='" + city + '\'' +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
