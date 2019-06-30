package com.perago.techtest.services;

import com.perago.techtest.Diff;
import com.perago.techtest.DiffEngine;
import com.perago.techtest.DiffException;
import com.perago.techtest.DiffRenderer;
import com.perago.techtest.test.Person;
import com.perago.techtest.test.Pet;
import org.junit.Before;
import org.junit.Test;


public class DiffEngineImpTest {

    private Person originalPerson = new Person();
    private Person modifiedPerson = new Person();
    private Pet originalPet = new Pet();

    @Before
    public void init(){
        originalPerson.setFirstName("Joe");
        originalPerson.setSurname("Soap");
       // originalPerson.setFriend(null);

        originalPet.setName("Sporty");
        originalPet.setType("Dog");
        //originalPerson.setPet(originalPet);

        modifiedPerson.setFirstName("Johanna");
        modifiedPerson.setSurname(null);
        modifiedPerson.setFriend(null);
    }


    @Test
    public void testPersonDiff() throws DiffException {
        DiffEngine diffEngine = new DiffEngineImp();

        DiffRenderer diffRenderer = new DiffRenderImpl();

        Diff<Person> diff = diffEngine.calculate(originalPerson, modifiedPerson);
        System.out.println(diffRenderer.render(diff));


    }

}