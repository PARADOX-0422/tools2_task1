package ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Stateless
@Path("/function")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CalculationService {
	 @PersistenceContext
     private EntityManager entityManager;
	 	
	  @POST
	  @Path("/calc")
	  public Response performCalculation(CalculationEntity calculationEntity) {
	    float result = 0;
	    switch (calculationEntity.getOperation()) {
	      case "+":
	        result = calculationEntity.getNumber1() + calculationEntity.getNumber2();
	        break;
	      case "-":
	        result = calculationEntity.getNumber1() - calculationEntity.getNumber2();
	        break;
	      case "*":
	        result = calculationEntity.getNumber1() * calculationEntity.getNumber2();
	        break;
	      case "/":
	          if (calculationEntity.getNumber2() == 0) {
	            return Response
	                .status(Response.Status.BAD_REQUEST)
	                .entity("Division by zero is not allowed")
	                .build();
	          }
	          result = (float)calculationEntity.getNumber1() / calculationEntity.getNumber2();
	          break;
	      default:
	    	  return Response
	                    .status(Response.Status.INTERNAL_SERVER_ERROR)
	                    .entity("Unsupported operation")
	                    .build();	    }
	    entityManager.persist(calculationEntity);
        return Response.ok(new ResultEntity(result)).build();
	  }
	  
	  @GET
	  @Path("/calculations")
	  public Response getCalculations() {
		
		        try {
		        	 String simpleQuery="SELECT c from CalculationEntity c";
		   		  	TypedQuery<CalculationEntity> query = entityManager.createQuery(simpleQuery, CalculationEntity.class);
		   		  	List<CalculationEntity> calculationEntities = query.getResultList();
		   		  	
		            return Response.ok(calculationEntities).build();
		        } catch (RuntimeException err) {
		            return Response
		                    .status(Response.Status.INTERNAL_SERVER_ERROR)
		                    .entity(err.getMessage())
		                    .build();
		        }
	  }
	  
}