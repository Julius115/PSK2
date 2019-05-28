package lt.vu.entities;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CarDAO {
    @Inject
    private EntityManager em;

    public void create(Car car) {
        em.persist(car);
    }

    public List<Car> getAllCars() {
        return em.createNamedQuery("Car.findAll", Car.class).getResultList();
    }

    public Car getCarById(Integer id){
        return em.find(Car.class, id);
    }

    public void updateAndFlush(Car car) {
        em.merge(car);
        em.flush();
    }
}
