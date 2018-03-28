package clientToSOAPws;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import net1.webservicex.*;
import net2.webservicex.*;

public class SOAPwsClient {
	
	public SOAPwsClient() {}
	
	private static ArrayList<String> list = new ArrayList<String>();
	static {
		list.add("us");
		list.add("lr");
		list.add("mm");
	}
	
	public String sendRequest(String cntr, String amt) throws IOException, URISyntaxException {
		Country country = new Country();
		CountrySoap countryServiceSOAP = country.getCountrySoap();
		String iso = countryServiceSOAP.getISOCountryCodeByCountyName(cntr);
		int indexOfCountryCode = iso.indexOf("CountryCode") + 11 + 1;
		String countryCode = iso.substring(indexOfCountryCode, indexOfCountryCode+2);
		
		double amount = Double.parseDouble(amt);
		double n = amount;
		
		if (list.contains(countryCode)) {
			LengthUnit unit = new LengthUnit();
			LengthUnitSoap lengthUnitSOAP = unit.getLengthUnitSoap();
			
			n = lengthUnitSOAP.changeLengthUnit(amount, Lengths.CENTIMETERS, Lengths.INCHES);
			double rounded = (double) Math.round(n * 100) / 100;
			return ""+rounded + " inches in " + cntr;
		}
		
		return ""+n + " centimeters in " + cntr;
	}
}
