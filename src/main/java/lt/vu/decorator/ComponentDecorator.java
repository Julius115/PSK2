package lt.vu.decorator;

import lt.vu.alternative.IComponent;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

@Decorator
public abstract class ComponentDecorator implements IComponent {
    @Inject
    @Delegate
    @Any
    IComponent message;

    public String iAm() {
        return message.iAm() + "ComponentDecorator \r\n";
    }
}
