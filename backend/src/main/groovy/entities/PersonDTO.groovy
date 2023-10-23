package entities

import groovy.transform.Canonical

@Canonical
class PersonDTO {

    Integer id
    String name
    String zipCode
    String city
    String state
    String country
    String description
    String email
    String password

}
