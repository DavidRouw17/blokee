package org.example.domain;

import org.example.App;
import org.example.exceptions.BadRequestException;
import org.example.resources.ContactsResource;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.net.URL;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Arquillian.class)
public class ContactDaoDBIT {

    @ArquillianResource
    private URL deploymentURL;

    private String contactsResource;
    private String contactsUri = "resources/contacts";


    @Inject
    private ContactDaoDB target;

    @Before
    public void setUp() throws Exception {
        contactsResource = deploymentURL + contactsUri;
        target.addContact(Contact.builder().firstName("David").surname("Rouw").email("dr@mail.nl").build());
    }

    @Deployment
    public static Archive<?> createDeployment() {
        WebArchive archive = ShrinkWrap.create(WebArchive.class, "ContactDaoDBIT.war")
                .addClass(App.class)
                .addClass(ContactsResource.class)
                .addClass(ContactDaoDB.class)
                .addClass(Contact.class)
                .addClass(BadRequestException.class)
                .addAsWebInfResource("persistence.xml", "classes/META-INF/persistence.xml");
        System.out.println(archive.toString(true));
        return archive;
    }

    @Test
    public void testIfAddingContactWorks() {
        //given
        int sizeBefore = target.getAll().size();
        //when
        target.addContact(Contact.builder().firstName("Sanne").surname("J").email("sj@mail.nl").build());
        //then
        int sizeAfter = target.getAll().size();
        assertThat(sizeBefore + 1, is(sizeAfter));
    }
}
