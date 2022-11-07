package Part_1;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Executor {
    private List<Airline> airlines;
    private List<Trip> trips;
    public Executor(List airlines, List trips){
        this.airlines = airlines;
        this.trips = trips;
    }

    public void save_to_file(){
        try{
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("Airport");
            document.appendChild(root);
            for (int i = 0; i < airlines.size(); i++) {
                Element airline = document.createElement("Airline");
                airline.setAttribute("code", Integer.toString(airlines.get(i).getCode()));
                airline.setAttribute("name", airlines.get(i).getName());
                root.appendChild(airline);
                for (int j = 0; j < trips.size(); j++) {
                    if(trips.get(j).getNumber_airline() == airlines.get(i).getCode()){
                        Element trip = document.createElement("trip");
                        trip.setAttribute("code", Integer.toString(trips.get(j).getCode()));
                        trip.setAttribute("city_from", trips.get(j).getCity_from());
                        trip.setAttribute("city_to", trips.get(j).getCity_to());
                        trip.setAttribute("price", Double.toString(trips.get(j).getPrice()));
                        trip.setAttribute("number_airline", Integer.toString(trips.get(j).getNumber_airline()));
                        airline.appendChild(trip);
                    }
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "WINDOWS-1251");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(document);
            StreamResult streamResult = new StreamResult(new File("src\\Part_1\\data.xml"));
            transformer.transform(domSource, streamResult);
        }catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }
    public void load_from_file(){
        try {
            SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema s = sf.newSchema(new File("src\\Part_1\\Airport.xsd"));
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setSchema(s);
            DocumentBuilder builder;
            builder = factory.newDocumentBuilder();
            builder.setErrorHandler(new SimpleErrorHandler());
            Document document = builder.parse("src\\Part_1\\data.xml");

            NodeList nodelist_airlines = document.getElementsByTagName("Airline");

            for (int i = 0; i < nodelist_airlines.getLength(); i++) {
                Element airline = (Element) nodelist_airlines.item(i);
                airlines.add(create_object_airline(airline));


                NodeList nodelist_trips = airline.getElementsByTagName("trip");
                for (int j = 0; j < nodelist_trips.getLength(); j++) {
                    Element trip = (Element) nodelist_trips.item(j);
                    trips.add(create_object_trip(trip));
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private Airline create_object_airline(Element el){
        int code = Integer.parseInt(el.getAttribute("code"));
        String name = el.getAttribute("name");
        Airline airline = new Airline(code,name);
        return airline;
    }
    private Trip create_object_trip(Element el){
        int code = Integer.parseInt(el.getAttribute("code"));
        String city_from = el.getAttribute("city_from");
        String city_to = el.getAttribute("city_to");
        double price = Double.parseDouble(el.getAttribute("price"));
        int number_airline = Integer.parseInt(el.getAttribute("number_airline"));
        Trip trip = new Trip(code, city_from, city_to, price, number_airline);
        return trip;
    }
    public Airline get_airline_by_id(int id){
        Airline airline = new Airline(-1,"");
        for (int i = 0; i < airlines.size(); i++) {
            if(airlines.get(i).getCode() == id){
                return airlines.get(i);
            }
        }
        return airline;
    }
    public List<Trip> get_trip_by_airline_id(int id){
        List<Trip> trips_equal_id_airline= new ArrayList<>();
        for (int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getNumber_airline() == id){
                trips_equal_id_airline.add(trips.get(i));
            }
        }
        return trips_equal_id_airline;
    }
    public Airline get_airline_by_index(int index){
        return airlines.get(index-1);
    }
    public List<Trip> get_trips_by_index(int index){
        List<Trip> trips_equal_index_airline= new ArrayList<>();
        for (int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getNumber_airline() == index){
                trips_equal_index_airline.add(trips.get(i));
            }
        }
        return trips_equal_index_airline;
    }
    public int get_count_airlines(){
        return airlines.size();
    }
    public void add_new_airline(Airline new_airline){
        airlines.add(new_airline);
        save_to_file();
    }
    public boolean delete_airline_by_id(int id){
        for (int i = 0; i < airlines.size(); i++) {
            if(airlines.get(i).getCode() == id){
                airlines.remove(airlines.get(i));
                save_to_file();
                return true;
            }
        }
        return false;
    }
    public void add_new_trip(Trip trip){
        trips.add(trip);
        save_to_file();
    }
    public boolean delete_trip(int id){
        for (int i = 0; i < trips.size(); i++) {
            if(trips.get(i).getCode() == id){
                trips.remove(trips.get(i));
                save_to_file();
                return true;
            }
        }
        return false;
    }
}
