package ru.job4j.serialization.json.my;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Arrays;
import java.util.StringJoiner;
import javax.xml.bind.annotation.*;

@XmlRootElement(name = "house")
@XmlAccessorType(XmlAccessType.FIELD)
public class House {

    private String address;
    private int buildYear;

    @XmlElementWrapper(name = "apartments")
    @XmlElement(name = "apartment")
    private Apartment[] apartments;

    private boolean isResidential;

    public House() {
    }

    public boolean isResidential() {
        return isResidential;
    }

    public String getAddress() {
        return address;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public Apartment[] getApartments() {
        return apartments;
    }

    public House(String address, int buildYear, Apartment[] apartments, boolean isResidential) {
        this.address = address;
        this.buildYear = buildYear;
        this.apartments = apartments;
        this.isResidential = isResidential;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", House.class.getSimpleName() + "[", "]")
                .add("address='" + address + "'")
                .add("buildYear=" + buildYear)
                .add("apartments=" + Arrays.toString(apartments))
                .add("isResidential=" + isResidential)
                .toString();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public void setApartments(Apartment[] apartments) {
        this.apartments = apartments;
    }

    public void setResidential(boolean residential) {
        isResidential = residential;
    }

    public static void main(String[] args) {
        var house = new House("Vozdvizhenka Street, 10, Moscow, 125009",
                1890,
                new Apartment[]{
                        new Apartment(1, new Lodger[]{
                                new Lodger("Sergey")
                        }),
                        new Apartment(2, new Lodger[]{
                                new Lodger("Andrey")
                        })},
                true);
        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(house));
        final var houseJson = """
                {
                   "address":"26 Bolshaya Dmitrovka Street, Moscow 103426 Russia",
                   "buildYear":1780,
                   "apartments":[
                      {
                         "number":1,
                         "lodgers":[
                            {
                               "name":"Alexei"
                            }
                         ]
                      },
                      {
                         "number":2,
                         "lodgers":[
                            {
                               "name":"Kirill"
                            }
                         ]
                      }
                   ],
                   "isResidential":false
                }
                """;
        final var houseMod = gson.fromJson(houseJson, House.class);
        System.out.println(houseMod);
    }
}
