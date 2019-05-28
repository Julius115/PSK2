package lt.vu.components;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import lt.vu.entities.*;
import org.primefaces.context.RequestContext;

import javax.annotation.PostConstruct;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Slf4j
public class EditCarController implements Serializable {

    /*
     * Būsena, kurią saugome ViewScoped kontekste:
     */
    @Getter private List<Car> allCars;
    @Getter private Car selectedCar;
    @Getter private Car conflictingCar;

    /*
     * DAO:
     */
    @Inject private CarDAO carDAO;

    @PostConstruct
    public void init() {
        reloadAll();
    }

    public void prepareForEditing(Car car) {
        selectedCar = car;
        conflictingCar = null;
    }

    @Transactional
    public void updateSelectedCar() {
        try {
            carDAO.updateAndFlush(selectedCar);
            reloadAll();
        } catch (OptimisticLockException ole) {
            conflictingCar = carDAO.getCarById(selectedCar.getId());
            // Pranešam PrimeFaces dialogui, kad užsidaryti dar negalima:
            RequestContext.getCurrentInstance().addCallbackParam("validationFailed", true);
        }
    }

    @Transactional
    public void overwriteCar() {
        selectedCar.setOptLockVersion(conflictingCar.getOptLockVersion());
        updateSelectedCar();
    }

    public void reloadAll() {
        allCars = carDAO.getAllCars();
    }
}
