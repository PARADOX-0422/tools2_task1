package ejbs;
import javax.persistence.*;
import java.io.*;
@Entity
public class CalculationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	  private int number1;
	  private int number2;
	  private String operation;
	  
	  public void setId(Long id) {
		  this.id=id;
	  }
	  
	  public void setNumber1(int n1) {
		  number1=n1;
	  }
	  
	  public void setNumber2(int n2) {
		  number2=n2;
	  }
	  
	  public void setOperation(String o) {
		  operation=o;
	  }
	  
	  public String getOperation() {
		  return operation;
	  }
	  
	  public int getNumber1() {
		  return number1;
	  }
	  
	  public int getNumber2() {
		  return number2;
	  }
}