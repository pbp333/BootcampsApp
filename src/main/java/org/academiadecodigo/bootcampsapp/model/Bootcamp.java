package org.academiadecodigo.bootcampsapp.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 10/11/17.
 */
public class Bootcamp {

    private Integer id;
    private List<CodeCadet> codeCadets;
    private String location;
    private Date start;
    private Date end;

    public Bootcamp(String location, Date start, Date end){

        this.location = location;
        this.start = start;
        this.end = end;
        codeCadets = new LinkedList<>();
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

}
