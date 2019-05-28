package lt.vu.alternative;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;

@ApplicationScoped
@Alternative
public class ComponentA implements IComponent {
    public String iAm() {
        return "ComponentA \r\n";
    }
}
