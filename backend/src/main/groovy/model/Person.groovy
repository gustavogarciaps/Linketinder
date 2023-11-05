package model

import groovy.transform.Canonical
import jakarta.json.bind.annotation.JsonbProperty

@Canonical
class Person {

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
