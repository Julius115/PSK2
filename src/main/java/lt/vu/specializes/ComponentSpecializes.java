package lt.vu.specializes;

import lt.vu.alternative.ComponentB;

import javax.enterprise.inject.Specializes;

@Specializes
public class ComponentSpecializes extends ComponentB {
    @Override
    public String iAm(){
        return super.iAm() + "ComponentSpecializes \r\n";
    }
}
