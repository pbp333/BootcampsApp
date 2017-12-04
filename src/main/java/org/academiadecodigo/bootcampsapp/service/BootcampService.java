package org.academiadecodigo.bootcampsapp.service;


import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;

import java.util.Date;
import java.util.List;

/**
 * Created by codecadet on 10/11/17.
 */
public interface BootcampService extends Service {

    void createBootcamp(String location, Date start, Date end);

    void addCodeCadet(int bootcampID, CodeCadet codeCadet);

    List<Bootcamp> getBootcamps();

    List<CodeCadet> getCodeCadets(int bootcampID);

    void addBootcamp(Bootcamp bootcamp);
}
