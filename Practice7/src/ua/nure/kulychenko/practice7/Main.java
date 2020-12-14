package ua.nure.kulychenko.practice7;

import java.util.List;

import ua.nure.kulychenko.practice7.controller.DOMController;
import ua.nure.kulychenko.practice7.controller.SAXController;
import ua.nure.kulychenko.practice7.controller.STAXController;
import ua.nure.kulychenko.practice7.entity.Tariff;
import ua.nure.kulychenko.practice7.util.Sorter;


public class Main {
	public static void usage() {
		System.out.println("java ua.nure.kulychenko.practice7.Main xmlFileName");
	}

	public static void main(String[] args) throws Exception {
		if (args.length != 1) {
			usage();
			return;
		}

		String xmlFileName = args[0];
		System.out.println("Input ==> " + xmlFileName);

		////////////////////////////////////////////////////////
		// DOM
		////////////////////////////////////////////////////////

		// get
		DOMController domController = new DOMController(xmlFileName);
		domController.parse(true);
		List<Tariff> tariffs = domController.getTariffs();

		// sort (case 1)
		Sorter.sortTariffsByNameText(tariffs);

		// save
		String outputXmlFile = "output.dom.xml";
		DOMController.saveToXML(tariffs, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		////////////////////////////////////////////////////////
		// SAX
		////////////////////////////////////////////////////////
		
		// get
		SAXController saxController = new SAXController(xmlFileName);
		saxController.parse(true);
		tariffs = saxController.getTariffs();
		
		// sort  (case 2)
		Sorter.sortTariffsByNameText(tariffs);
		
		// save
		outputXmlFile = "output.sax.xml";
		
		// other way: 
		DOMController.saveToXML(tariffs, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);

		////////////////////////////////////////////////////////
		// StAX
		////////////////////////////////////////////////////////
		
		// get
		STAXController staxController = new STAXController(xmlFileName);
		staxController.parse();
		tariffs = staxController.getTariffs();
		
		// sort  (case 3)
		Sorter.sortTariffsByNameText(tariffs);
		
		// save
		outputXmlFile = "output.stax.xml";
		DOMController.saveToXML(tariffs, outputXmlFile);
		System.out.println("Output ==> " + outputXmlFile);


	}
}
