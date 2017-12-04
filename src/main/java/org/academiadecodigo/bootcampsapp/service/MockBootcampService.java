package org.academiadecodigo.bootcampsapp.service;


import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by codecadet on 10/11/17.
 */
public class MockBootcampService implements BootcampService {

    private List<Bootcamp> bootcamps;
    private int numBootcamps = 0;

    public MockBootcampService() {

        bootcamps = new LinkedList<>();
    }

    @Override
    public void createBootcamp(String location, Date start, Date end) {

        Bootcamp bootcamp = new Bootcamp();
        bootcamp.setupBootcamp(location, start, end);
        bootcamp.setId(numBootcamps);
        bootcamps.add(bootcamp);
        numBootcamps++;
    }

    @Override
    public void addCodeCadet(int bootcampID, CodeCadet codeCadet) {

        if (bootcamps.get(bootcampID) == null) {
            return;
        }
        bootcamps.get(bootcampID).addCadet(codeCadet);
    }

    @Override
    public List<Bootcamp> getBootcamps() {

        return bootcamps;
    }

    @Override
    public List<CodeCadet> getCodeCadets(int bootcampID) {

        if (bootcampID > numBootcamps) {
            return new LinkedList<>();

        }
        return bootcamps.get(bootcampID).getCodeCadets();
    }

    @Override
    public void addBootcamp(Bootcamp bootcamp) {
        bootcamps.add(bootcamp);
    }
}
