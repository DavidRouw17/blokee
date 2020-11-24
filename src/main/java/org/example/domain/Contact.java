package org.example.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    private String firstName;
    private String surname;
    private String email;
    private int id;
    private boolean edit;

    public String allDataText(){
        return firstName.toLowerCase() + " " + surname.toLowerCase() + " " + email.toLowerCase();
    }

}
