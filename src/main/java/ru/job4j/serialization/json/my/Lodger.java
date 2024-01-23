package ru.job4j.serialization.json.my;

import java.util.StringJoiner;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "lodger")
@XmlAccessorType(XmlAccessType.FIELD)
public class Lodger {

    @XmlElement
    private String name;

    public Lodger(String name) {
        this.name = name;
    }

    public Lodger() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Lodger.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
