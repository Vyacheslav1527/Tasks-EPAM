package ua.nure.kulychenko.practice7.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.kulychenko.practice7.constants.Constants;
import ua.nure.kulychenko.practice7.constants.Names;
import ua.nure.kulychenko.practice7.entity.Parameter;
import ua.nure.kulychenko.practice7.entity.Payment;
import ua.nure.kulychenko.practice7.entity.Price;
import ua.nure.kulychenko.practice7.entity.Tariff;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller for DOM parser.
 */
public class DOMController {

    private String xmlFileName;

    // main container
    private List<Tariff> tariffs;

    public DOMController(String xmlFileName) {
        this.xmlFileName = xmlFileName;
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    /**
     * Parses XML document.
     *
     * @param validate If true validate XML document against its XML schema.
     */
    public void parse(boolean validate)
            throws ParserConfigurationException, SAXException, IOException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        // make parser validating
        if (validate) {
            // turn validation on
            dbf.setFeature(Constants.FEATURE_TURN_VALIDATION_ON, true);

            // turn on xsd validation
            dbf.setFeature(Constants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
        }

        DocumentBuilder db = dbf.newDocumentBuilder();

        // set error handler
        db.setErrorHandler(new DefaultHandler() {
            @Override
            public void error(SAXParseException e) throws SAXException {
                // throw exception if XML document is NOT valid
                throw e;
            }
        });

        // parse XML document
        Document document = db.parse(xmlFileName);

        // get root element
        Element root = document.getDocumentElement();

        // create container
        tariffs = new ArrayList<>();

        // obtain tariffs nodes
        NodeList tariffNodes = root
                .getElementsByTagName(Names.TARIFF);

        // process tariffs nodes
        for (int j = 0; j < tariffNodes.getLength(); j++) {
            Tariff tariff = getTariff(tariffNodes.item(j));
            // add tariff to container
            tariffs.add(tariff);
        }
    }

    /**
     * Extracts tariff object from the question XML node.
     *
     * @param qNode Tariff node.
     * @return Tariff object.
     */
    private static Tariff getTariff(Node qNode) {
        Tariff tariff = new Tariff();
        Element qElement = (Element) qNode;

        // process tariff name
        Node qtNode = qElement.getElementsByTagName(Names.TARIFF_NAME)
                .item(0);
        // set tariff name
        tariff.setName(qtNode.getTextContent());

        //process operator name
        qtNode = qElement.getElementsByTagName(Names.OPERATOR_NAME)
                .item(0);
        //set operator name
        tariff.setOperatorName(qtNode.getTextContent());

        //process payroll
        qtNode = qElement.getElementsByTagName(Names.TARIFF_PAYROLL)
                .item(0);
        //set payroll
        tariff.setPayroll(getPayment(qtNode));

        //process call prices
        qtNode = qElement.getElementsByTagName(Names.CALL_PRICES)
                .item(0);
        //set call prices
        tariff.setCallPrices(getPrices(qtNode));

        //process sms prices
        qtNode = qElement.getElementsByTagName(Names.SMS_PRICES)
                .item(0);
        //set sms prices
        tariff.setSmsPrices(getPrices(qtNode));

        //process parameters
        qtNode = qElement.getElementsByTagName(Names.PARAMETERS).item(0);
        //set parameters
        tariff.setParameters(getParameters(qtNode));

        return tariff;
    }

    private static Payment getPayment(Node qNode) {
        Payment payment = new Payment();
        Element qElement = (Element) qNode;

        //process and set payment
        double value = Double.parseDouble(qElement.getTextContent());
        payment.setValue(value);
        String currency = qElement.getAttribute(Names.PAYMENT_CURRENCY);
        payment.setCurrency(currency);
        String scale = qElement.getAttribute(Names.PAYMENT_SCALE);
        payment.setScale(scale);
        return payment;
    }

    private static Price getPrice(Node qNode) {
        Price price = new Price();
        Element qElement = (Element) qNode;

        //process price type and set values
        String type = qElement.getAttribute(Names.PRICE_TYPE);
        price.setType(type);
        String netBorder = qElement.getAttribute(Names.PRICE_NET_BORDER);
        price.setNetBorder(netBorder);
        //Process payment
        Node qtNode = qElement.getElementsByTagName(Names.PAYMENT).item(0);
        Payment payment = getPayment(qtNode);
        price.setPayment(payment);

        return price;
    }

    private static List<Price> getPrices(Node qNode) {
        List<Price> prices = new ArrayList<>();
        Element qElement = (Element) qNode;

        NodeList nodeList = qElement.getElementsByTagName(Names.PRICE);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Price price = getPrice(nodeList.item(i));
            prices.add(price);
        }

        return prices;
    }

    private static Parameter getParameter(Node qNode) {
        Parameter parameter = new Parameter();
        Element qElement = (Element) qNode;

        //process parameter and set values
        String value = qElement.getTextContent();
        parameter.setValue(value);
        String type = qElement.getAttribute(Names.PARAMETER_TYPE);
        parameter.setType(type);
        String unit = qElement.getAttribute(Names.PARAMETER_UNIT);
        parameter.setUnit(unit);

        return parameter;
    }

    private static List<Parameter> getParameters(Node qNode) {
        List<Parameter> parameters = new ArrayList<>();
        Element qElement = (Element) qNode;

        //process parameters
        NodeList nodeList = qElement.getElementsByTagName(Names.PARAMETER);
        for (int i = 0; i < nodeList.getLength(); i++) {
            Parameter parameter = getParameter(nodeList.item(i));
            parameters.add(parameter);
        }
        return parameters;
    }

    // //////////////////////////////////////////////////////
    // Static util methods
    // //////////////////////////////////////////////////////

    /**
     * Creates and returns DOM of the List<Tariff> container.
     *
     * @param tariffs list container.
     * @throws ParserConfigurationException
     */
    public static Document getDocument(List tariffs) throws ParserConfigurationException {

        // obtain DOM parser
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        // set properties for Factory

        // XML document contains namespaces
        dbf.setNamespaceAware(true);

        DocumentBuilder db = dbf.newDocumentBuilder();
        Document document = db.newDocument();

        // create root element
        Element tElement = document.createElement(Names.TARIFFS);

        // add root element
        document.appendChild(tElement);

        // add questions elements
        for (Object o : tariffs) {
            Tariff tariff = (Tariff) o;
            // add question
            Element qElement = document.createElement(Names.TARIFF);
            tElement.appendChild(qElement);

            // add question text
            Element qtElement =
                    document.createElement(Names.TARIFF_NAME);
            qtElement.setTextContent(tariff.getName());
            qElement.appendChild(qtElement);

            qtElement = document.createElement(Names.OPERATOR_NAME);
            qtElement.setTextContent(tariff.getOperatorName());
            qElement.appendChild(qtElement);

            qtElement = document.createElement(Names.TARIFF_PAYROLL);
            qtElement.setTextContent(String.valueOf(tariff.getPayroll().getValue()));
            qtElement.setAttribute(Names.PAYMENT_SCALE, tariff.getPayroll().getScale());
            qtElement.setAttribute(Names.PAYMENT_CURRENCY, tariff.getPayroll().getCurrency());
            qElement.appendChild(qtElement);

            qtElement = document.createElement(Names.CALL_PRICES);

            //add callPrices
            for (Price price : tariff.getCallPrices()) {
                Element priceElement = document.createElement(Names.PRICE);
                priceElement.setAttribute(Names.PRICE_TYPE, price.getType());
                if (!"".equals(price.getNetBorder()) && price.getNetBorder() != null) {
                    priceElement.setAttribute(Names.PRICE_NET_BORDER, price.getNetBorder());
                }
                Payment payment = price.getPayment();
                Element paymentElement = document.createElement(Names.PAYMENT);
                paymentElement.setTextContent(String.valueOf(payment.getValue()));
                paymentElement.setAttribute(Names.PAYMENT_SCALE, payment.getScale());
                paymentElement.setAttribute(Names.PAYMENT_CURRENCY, payment.getCurrency());
                priceElement.appendChild(paymentElement);
                qtElement.appendChild(priceElement);
            }
            qElement.appendChild(qtElement);

            qtElement = document.createElement(Names.SMS_PRICES);

            //add smsPrices
            for (Price price : tariff.getSmsPrices()) {
                Element priceElement = document.createElement(Names.PRICE);
                priceElement.setAttribute(Names.PRICE_TYPE, price.getType());
                if (!"".equals(price.getNetBorder()) && price.getNetBorder() != null) {
                    priceElement.setAttribute(Names.PRICE_NET_BORDER, price.getNetBorder());
                }
                Payment payment = price.getPayment();
                Element paymentElement = document.createElement(Names.PAYMENT);
                paymentElement.setTextContent(String.valueOf(payment.getValue()));
                paymentElement.setAttribute(Names.PAYMENT_SCALE, payment.getScale());
                paymentElement.setAttribute(Names.PAYMENT_CURRENCY, payment.getCurrency());
                priceElement.appendChild(paymentElement);
                qtElement.appendChild(priceElement);
            }
            qElement.appendChild(qtElement);

            //add parameters
            qtElement = document.createElement(Names.PARAMETERS);

            for (Parameter parameter : tariff.getParameters()) {
                Element parameterElement = document.createElement(Names.PARAMETER);
                parameterElement.setTextContent(parameter.getValue());
                parameterElement.setAttribute(Names.PARAMETER_TYPE, parameter.getType());
                if (!"".equals(parameter.getUnit()) && parameter.getUnit() != null) {
                    parameterElement.setAttribute(Names.PARAMETER_UNIT, parameter.getUnit());
                }
                qtElement.appendChild(parameterElement);
            }
            qElement.appendChild(qtElement);
        }
        return document;
    }

    /**
     * Saves List<Tariff> object to XML file.
     *
     * @param tariffs     Tariffs object to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(List<Tariff> tariffs, String xmlFileName)
            throws ParserConfigurationException, TransformerException {
        // Test -> DOM -> XML
        saveToXML(getDocument(tariffs), xmlFileName);
    }

    /**
     * Save DOM to XML.
     *
     * @param document    DOM to be saved.
     * @param xmlFileName Output XML file name.
     */
    public static void saveToXML(Document document, String xmlFileName)
            throws TransformerException {

        StreamResult result = new StreamResult(new File(xmlFileName));

        // set up transformation
        TransformerFactory tf = TransformerFactory.newInstance();
        javax.xml.transform.Transformer t = tf.newTransformer();
        t.setOutputProperty(OutputKeys.INDENT, "yes");

        // run transformation
        t.transform(new DOMSource(document), result);
    }

    public static void main(String[] args) throws Exception {

        // try to parse NOT valid XML document with validation on (failed)
        DOMController domContr = new DOMController(Constants.INVALID_XML_FILE);
        try {
            // parse with validation (failed)
            domContr.parse(true);
        } catch (SAXException ex) {
            System.err.println("====================================");
            System.err.println("XML not valid");
            System.err.println("Test object --> " + domContr.getTariffs());
            System.err.println("====================================");
        }

        // try to parse NOT valid XML document with validation off (success)
        domContr.parse(false);

        // we have Test object at this point:
        System.out.println("====================================");
        System.out.print("Here is the test: \n" + domContr.getTariffs());
        System.out.println("====================================");

        // save test in XML file
        List<Tariff> tariffs = domContr.getTariffs();
        DOMController.saveToXML(tariffs, Constants.INVALID_XML_FILE + ".dom-result.xml");
    }
}
