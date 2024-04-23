package ejbs;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;

@ApplicationPath("/api")
public class App extends Application {
	public static void main(String[] args) {
		
		CalculationService calculationService = new CalculationService();

	    CalculationEntity calculationEntity = new CalculationEntity();
	    calculationEntity.setNumber1(10);
	    calculationEntity.setNumber2(5);
	    calculationEntity.setOperation("/");

	    Response response = calculationService.performCalculation(calculationEntity);

	    
	    if (response.getStatus() == Response.Status.OK.getStatusCode()) {
	      ResultEntity result = (ResultEntity) response.getEntity();
	      System.out.println("Result: " + result.getResult());
	    } else {
	      System.out.println("Error: " + response.getStatusInfo().getReasonPhrase());
	    }
	  }
}