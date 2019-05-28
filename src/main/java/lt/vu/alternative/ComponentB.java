package lt.vu.alternative;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class ComponentB implements IComponent {
    public String iAm() {
        return "ComponentB \r\n";
    }
}
