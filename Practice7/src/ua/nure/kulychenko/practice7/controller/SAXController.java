package ua.nure.kulychenko.practice7.controller;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.kulychenko.practice7.constants.Constants;
import ua.nure.kulychenko.practice7.constants.Names;
import ua.nure.kulychenko.practice7.entity.Parameter;
import ua.nure.kulychenko.practice7.entity.Payment;
import ua.nure.kulychenko.practice7.entity.Price;
import ua.nure.kulychenko.practice7.entity.Tariff;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for SAX parser.
 */
public class SAXController extends DefaultHandler {

    private String xmlFileName;

    // current element name holder
    private String currentElement;

    // main container
    private List<Tariff> tariffs;

    private Tariff tariff;

    private Price price;

    private List<Price> callPrices;

    private List<Price> smsPrices;

    private Payment payment;

    private Parameter parameter;

    private List<Parameter> parameters;

    public SAXController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema. With
     *                 this parameter it is possible make parser validating.
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain sax parser factory
        SAXParserFactory factory = SAXParserFactory.newInstance();

        // XML document contains namespaces
        factory.setNamespaceAware(true);

        // set validation
        if (validate) {
            factory.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);
            factory.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        SAXParser parser = factory.newSAXParser();
        parser.parse(xmlFileName, this);
    }

    // ///////////////////////////////////////////////////////////
    // ERROR HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////

    @Override
    public void error(org.xml.sax.SAXParseException e) throws SAXException {
        // if XML document not valid just throw exception
        throw e;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    // ///////////////////////////////////////////////////////////
    // CONTENT HANDLER IMPLEMENTATION
    // ///////////////////////////////////////////////////////////


    @Override
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {

        currentElement = localName;

        if (Names.TARIFFS.equals(currentElement)) {
            tariffs = new ArrayList<>();
            return;
        }

        if (Names.TARIFF.equals(currentElement)) {
            tariff = new Tariff();
            return;
        }

        if (Names.TARIFF_PAYROLL.equals(currentElement)) {
            payment = new Payment();
            if (attributes.getLength() > 0) {
                payment.setScale(attributes.getValue(uri, Names.PAYMENT_SCALE));
                payment.setCurrency(attributes.getValue(uri, Names.PAYMENT_CURRENCY));
            }
            return;
        }

        if (Names.PAYMENT.equals(currentElement)) {
            payment = new Payment();
            if (attributes.getLength() > 0) {
                payment.setScale(attributes.getValue(uri, Names.PAYMENT_SCALE));
                payment.setCurrency(attributes.getValue(uri, Names.PAYMENT_CURRENCY));
            }
            return;
        }

        if (Names.CALL_PRICES.equals(currentElement)) {
            callPrices = new ArrayList<>();
            return;
        }

        if (Names.SMS_PRICES.equals(currentElement)) {
            smsPrices = new ArrayList<>();
            return;
        }

        if (Names.PARAMETERS.equals(currentElement)) {
            parameters = new ArrayList<>();
            return;
        }

        if (Names.PRICE.equals(currentElement)) {
            price = new Price();
            if (attributes.getLength() > 0) {
                price.setType(attributes.getValue(uri, Names.PRICE_TYPE));
                price.setNetBorder(attributes.getValue(uri, Names.PRICE_NET_BORDER));
            }
            return;
        }

        if (Names.PARAMETER.equals(currentElement)) {
            parameter = new Parameter();
            if (attributes.getLength() > 0) {
                parameter.setType(attributes.getValue(uri, Names.PARAMETER_TYPE));
                parameter.setUnit(attributes.getValue(uri, Names.PARAMETER_UNIT));
            }
        }
    }

    @Override
    public void characters(char[] ch, int start, int length)
            throws SAXException {

        String elementText = new String(ch, start, length).trim();

        // return if content is empty
        if (elementText.isEmpty()) {
            return;
        }

        if (Names.TARIFF_NAME.equals(currentElement)) {
            tariff.setName(elementText);
            return;
        }

        if (Names.OPERATOR_NAME.equals(currentElement)) {
            tariff.setOperatorName(elementText);
            return;
        }

        if (Names.TARIFF_PAYROLL.equals(currentElement)) {
            payment.setValue(Double.parseDouble(elementText));
            return;
        }

        if (Names.PAYMENT.equals(currentElement)) {
            payment.setValue(Double.parseDouble(elementText));
            price.setPayment(payment);
            return;
        }

        if (Names.PARAMETER.equals(currentElement)) {
            parameter.setValue(elementText);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (Names.PRICE.equals(localName)) {
            if (price.getType().equals(Names.PRICE_CALL)) {
                callPrices.add(price);
                return;
            }
            smsPrices.add(price);
            return;
        }

        if (Names.PARAMETER.equals(localName)) {
            parameters.add(parameter);
            return;
        }

        if (Names.CALL_PRICES.equals(localName)) {
            tariff.setCallPrices(callPrices);
            return;
        }

        if (Names.SMS_PRICES.equals(localName)) {
            tariff.setSmsPrices(smsPrices);
            return;
        }

        if (Names.PARAMETERS.equals(localName)) {
            tariff.setParameters(parameters);
            return;
        }

        if (Names.TARIFF_PAYROLL.equals(localName)) {
            tariff.setPayroll(payment);
            return;
        }

        if (Names.TARIFF.equals(localName)) {
            tariffs.add(tariff);
        }
    }

    public static void main(String[] args) throws Exception {

        // try to parse valid XML file (success)
        SAXController saxContr = new SAXController(Constants.VALID_XML_FILE);

        // do parse with validation on (success)
        saxContr.parse(true);

        // obtain container
        List<Tariff> tariffs = saxContr.getTariffs();

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + tariffs);
        System.out.println("====================================");

        // now try to parse NOT valid XML (failed)
        saxContr = new SAXController(Constants.INVALID_XML_FILE);
        try {
            // do parse with validation on (failed)
            saxContr.parse(true);
        } catch (Exception ex) {
            System.err.println("====================================");
            System.err.println("Validation is failed:\n" + ex.getMessage());
            System.err
                    .println("Try to print test object:" + saxContr.getTariffs());
            System.err.println("====================================");
        }

        // and now try to parse NOT valid XML with validation off (success)
        saxContr.parse(false);

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + saxContr.getTariffs());
        System.out.println("====================================");
    }
}