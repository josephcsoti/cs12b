/*------------------------------------------------
File Name: DuplicateKeyException.java
Desc:  Exception for dup keys in Dictionary.java
Instructions: None

Name:   Joseph Csoti
CruzID: 1617438
Class:  CMPS 12B
Date:   02/02/18
------------------------------------------------*/

public class DuplicateKeyException extends RuntimeException {

  DuplicateKeyException(String message){
    super(message);
  }
  
}