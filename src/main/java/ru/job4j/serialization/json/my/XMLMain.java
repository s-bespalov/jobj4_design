package ru.job4j.serialization.json.my;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class XMLMain {
    public static void main(String[] args) throws Exception {
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
        var context = JAXBContext.newInstance(House.class);
        var marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        var xml = "";
        try (var writer = new StringWriter()) {
            marshaller.marshal(house, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        var unmarshaller = context.createUnmarshaller();
        try (var reader = new StringReader(xml)) {
            var result = (House) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}