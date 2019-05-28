package lt.vu.components;

import lombok.extern.slf4j.Slf4j;
import lt.vu.alternative.IComponent;
import lt.vu.interceptors.Logged;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ApplicationScoped
@Slf4j
public class ExtensibilityController {

    @Inject
    IComponent component;

    @Logged
    public String containerAsString() {
        return component.iAm();
    }
}
