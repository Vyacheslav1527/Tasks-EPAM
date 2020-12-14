package ua.nure.kulychenko.practice7.controller;

import org.xml.sax.helpers.DefaultHandler;

import ua.nure.kulychenko.practice7.constants.Constants;
import ua.nure.kulychenko.practice7.constants.Names;
import ua.nure.kulychenko.practice7.entity.Parameter;
import ua.nure.kulychenko.practice7.entity.Payment;
import ua.nure.kulychenko.practice7.entity.Price;
import ua.nure.kulychenko.practice7.entity.Tariff;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.stream.events.Characters;
import javax.xml.transform.stream.StreamSource;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for StAX parser.
 */
public class STAXController extends DefaultHandler {

    private String xmlFileName;

    // main container
    private List<Tariff> tariffs;

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public STAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document with StAX (based on event reader). There is no validation during the
     * parsing.
     */
    public void parse() throws XMLStreamException {

        Tariff tariff = null;
        Price price = null;
        List<Price> callPrices = null;
        List<Price> smsPrices = null;
        Payment payment = null;
        Parameter parameter = null;
        List<Parameter> parameters = null;

        // current element name holder
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();

        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);

        XMLEventReader reader = factory.createXMLEventReader(
                new StreamSource(xmlFileName));

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();

            // skip any empty content
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }

            // handler for start tags
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();

                if (Names.TARIFFS.equals(currentElement)) {
                    tariffs = new ArrayList<>();
                    continue;
                }

                if (Names.TARIFF.equals(currentElement)) {
                    tariff = new Tariff();
                    continue;
                }

                if (Names.TARIFF_PAYROLL.equals(currentElement)) {
                    payment = new Payment();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Names.PAYMENT_SCALE));
                    if (attribute != null) {
                        payment.setScale(attribute.getValue());
                    }
                    attribute = startElement.getAttributeByName(
                            new QName(Names.PAYMENT_CURRENCY));
                    if (attribute != null) {
                        payment.setCurrency(attribute.getValue());
                    }
                    continue;
                }

                if (Names.PAYMENT.equals(currentElement)) {
                    payment = new Payment();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Names.PAYMENT_SCALE));
                    if (attribute != null) {
                        payment.setScale(attribute.getValue());
                    }
                    attribute = startElement.getAttributeByName(
                            new QName(Names.PAYMENT_CURRENCY));
                    if (attribute != null) {
                        payment.setCurrency(attribute.getValue());
                    }
                    continue;
                }

                if (Names.CALL_PRICES.equals(currentElement)) {
                    callPrices = new ArrayList<>();
                    continue;
                }

                if (Names.SMS_PRICES.equals(currentElement)) {
                    smsPrices = new ArrayList<>();
                    continue;
                }

                if (Names.PARAMETERS.equals(currentElement)) {
                    parameters = new ArrayList<>();
                    continue;
                }

                if (Names.PRICE.equals(currentElement)) {
                    price = new Price();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Names.PRICE_TYPE));
                    if (attribute != null) {
                        price.setType(attribute.getValue());
                    }
                    attribute = startElement.getAttributeByName(
                            new QName(Names.PRICE_NET_BORDER));
                    if (attribute != null) {
                        price.setNetBorder(attribute.getValue());
                    }
                }

                if (Names.PARAMETER.equals(currentElement)) {
                    parameter = new Parameter();
                    Attribute attribute = startElement.getAttributeByName(
                            new QName(Names.PARAMETER_TYPE));
                    if (attribute != null) {
                        parameter.setType(attribute.getValue());
                    }
                    attribute = startElement.getAttributeByName(
                            new QName(Names.PARAMETER_UNIT));
                    if (attribute != null) {
                        parameter.setUnit(attribute.getValue());
                    }
                    continue;
                }
            }

            // handler for contents
            if (event.isCharacters()) {
                Characters characters = event.asCharacters();

                if (Names.TARIFF_NAME.equals(currentElement)) {
                    tariff.setName(characters.getData());
                    continue;
                }

                if (Names.OPERATOR_NAME.equals(currentElement)) {
                    tariff.setOperatorName(characters.getData());
                    continue;
                }

                if (Names.TARIFF_PAYROLL.equals(currentElement)) {
                    payment.setValue(Double.parseDouble(characters.getData()));
                    continue;
                }

                if (Names.PAYMENT.equals(currentElement)) {
                    payment.setValue(Double.parseDouble(characters.getData()));
                    price.setPayment(payment);
                    continue;
                }

                if (Names.PARAMETER.equals(currentElement)) {
                    parameter.setValue(characters.getData());
                    continue;
                }
            }

            // handler for end tags
            if (event.isEndElement()) {
                EndElement endElement = event.asEndElement();
                String localName = endElement.getName().getLocalPart();

                if (Names.PRICE.equals(localName)) {
                    if (price.getType().equals(Names.PRICE_CALL)) {
                        callPrices.add(price);
                        continue;
                    }
                    smsPrices.add(price);
                    continue;
                }

                if (Names.PARAMETER.equals(localName)) {
                    parameters.add(parameter);
                    continue;
                }

                if (Names.CALL_PRICES.equals(localName)) {
                    tariff.setCallPrices(callPrices);
                    continue;
                }

                if (Names.SMS_PRICES.equals(localName)) {
                    tariff.setSmsPrices(smsPrices);
                    continue;
                }

                if (Names.PARAMETERS.equals(localName)) {
                    tariff.setParameters(parameters);
                    continue;
                }

                if (Names.TARIFF_PAYROLL.equals(localName)) {
                    tariff.setPayroll(payment);
                    continue;
                }

                if (Names.TARIFF.equals(localName)) {
                    tariffs.add(tariff);
                }

            }
        }
        reader.close();
    }

    public static void main(String[] args) throws Exception {

        // try to parse (valid) XML file (success)
        STAXController staxContr = new STAXController(Constants.VALID_XML_FILE);
        staxContr.parse(); // <-- do parse (success)

        // obtain container
        List<Tariff> tariffs = staxContr.getTariffs();

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + tariffs);
        System.out.println("====================================");
    }
}