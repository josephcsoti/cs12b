/*------------------------------------------------
File Name: KeyNotFoundException.java
Desc:  Exception for unexisting(null) keys in Dictionary.java
Instructions: None

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   02/02/18
------------------------------------------------*/

public class KeyNotFoundException extends RuntimeException {

  KeyNotFoundException(String message){
    super(message);
  }

}