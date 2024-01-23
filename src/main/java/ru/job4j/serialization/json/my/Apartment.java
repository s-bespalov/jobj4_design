package ru.job4j.serialization.json.my;

import java.util.Arrays;
import java.util.StringJoiner;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "apartment")
@XmlAccessorType(XmlAccessType.FIELD)
public class Apartment {

    private int number;

    @XmlElementWrapper(name = "lodgers")
    @XmlElement(name = "lodger")
    private Lodger[] lodgers;

    public int getNumber() {
        return number;
    }

    public Lodger[] getLodgers() {
        return lodgers;
    }

    public Apartment() {
    }

    public Apartment(int number, Lodger[] lodgers) {
        this.number = number;
        this.lodgers = lodgers;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setLodgers(Lodger[] lodgers) {
        this.lodgers = lodgers;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Apartment.class.getSimpleName() + "[", "]")
                .add("number=" + number)
                .add("lodgers=" + Arrays.toString(lodgers))
                .toString();
    }
}
