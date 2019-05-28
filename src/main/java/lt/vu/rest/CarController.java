package lt.vu.rest;

import lt.vu.entities.Car;
import lt.vu.entities.CarDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
@Path("car")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarController {

    @Inject
    private CarDAO carDAO;

    @GET
    public List<Car> getAll(){
        return carDAO.getAllCars();
    }

    @GET
    @Path("{id}")
    public Response getId(@PathParam("id") Integer id) {
        Car car = carDAO.getCarById(id);
        if (car != null) {
            return Response.status(Response.Status.OK).entity(car).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @POST
    @Transactional
    public Car create(Car car){
        carDAO.create(car);
        return car;
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Response update(Car car, @PathParam("id") Integer id){
        if (car.getId() != null && !car.getId().equals(id))
            return Response.status(Response.Status.BAD_REQUEST).entity("Id do not match").build();

        Car carToUpdate = carDAO.getCarById(id);
        if (carToUpdate == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        carToUpdate.setModel(car.getModel());
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setNumber(car.getNumber());

        carDAO.updateAndFlush(carToUpdate);
        return Response.status(Response.Status.OK).entity(carToUpdate).build();
    }
}
