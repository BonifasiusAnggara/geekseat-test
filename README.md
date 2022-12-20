# geekseat-test
A Story of The Witch

### Guides

The following guides illustrate how to use some features concretely:

* [To use the endpoint please make a post request to]
  http://localhost:8080/witch/death-average
* with body:
  {
    persons: [
      {
        "ageOfDeath": 10,
        "yearOfDeath": 12
      },
      {
        "ageOfDeath": 13,
        "yearOfDeath": 17
      }
    ]
  }
* [Or you can just run the test]
  com.geekseat.test.GeekseatTestApplicationTest and com.geekseat.test.RestApiTest

## Revision 1:

* Changing PersonDto form only person1 and person2 into List of PersonDto
* Make fibonacci function to using recursion
* This part I don't clearly understand:

If you give invalid data (i.e. negative age, person who born before the witch took control) the 
program should return -1.

* Is the negative is YearOfDeath - AgeOfDeath or
* The AgeOfDeath itself?

* So I decided to make if AgeOfDeath or YearOfDeath or YearOfDeath - AgeOfDeath is a negative value
* the PersonDto.getWitchYear will return -1

## Test Consul

* For The Function tests please check the GeekseatTestApplicationTests
* For the REST Controller Test please Heck the RestApiTest
