package org.example.domain;

import lombok.*;
import org.hibernate.annotations.Generated;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NamedQuery(name = "Contact.findAll", query = "select c from Contact c")
public class Contact {

    @Id
    @GeneratedValue
    private int id;

    private String firstName;
    private String surname;
    private String email;

    private boolean edit;

    public String allDataText(){
        return firstName.toLowerCase() + " " + surname.toLowerCase() + " " + email.toLowerCase();
    }

}
