

import java.util.ArrayList;

public class Suspect {
	private String name;
	private String codeName;
	private String country;
	private String town;
	
	private ArrayList<String> PhoneCalls = new ArrayList<>();  //τα νουμερα που χρησιμοποιει ο ύποπτος	
	private ArrayList<Suspect> Partners = new ArrayList<>();   // οι πιθανοι συνεργάτες του.
	private ArrayList<Suspect> suggestedPartners = new ArrayList<>();
	
	public Suspect(String aname,String acodeName,String acountry,String atown) {
		name = aname;
		codeName = acodeName;
		country = acountry;
		town = atown;
	}
	
	public void addNumber(String number){   // μεθοδος που καταχωρει ενα νουμερο στις επαφες του υποπτου
		PhoneCalls.add(number);
	}
	
	public ArrayList<String> phones(){   // μεθοδος που επιστρεφει μια λιστα με τις επαφεςσ του υποπτου 
		return PhoneCalls;
	}
		
	
	public void addPartners(Suspect suspect) {  // μεθοδος που καταχωρεί εναν συνεργατη στην λιστα
		boolean exist = false;
		for(Suspect t : Partners) {
			if (t == suspect)
				exist = true;
		}
		if (exist == false)   // ελενγχος εαν υπαρχει ηδη μεσα .
			Partners.add(suspect);
					
	}
	public String getName() {
		return name;
	}

	public String getCodeName() {
		return codeName;
	}
	
	public boolean isConnectedTo(Suspect aSuspect){
		for(Suspect suspects :Partners) {
			if (suspects == aSuspect) {
				return true;
			}
		}
		return false;
	}
	
	public ArrayList<Suspect> getCommonPartners(Suspect aSuspect) {
		ArrayList<Suspect> susp = new ArrayList<>();
		for(Suspect s : Partners) {
			for(Suspect spartners: aSuspect.Partners) {
				if(s == spartners)
					susp.add(s);
			}
		}
		return susp;
	}
		
	public int getCountPartners(){
		return Partners.size();
	}
	public String getCountry() {
		return country;
	}
	public void printPartners() {                              //Μέθοδο η οποία τυπώνει τους πιθανούς συνεργάτες (ονόματα και κωδικά ονόματα) του υπόπτου, με 
		                                                                 //έναν αστερίσκο (*) μετά το κωδικό όνομα στην περίπτωση που κατάγονται από την ίδια χώρα. 
		System.out.println("Partners : ");
		for(Suspect partners: Partners) {
			if (country == partners.getCountry()) {
				System.out.println(partners.getName() + " (" + partners.getCodeName() + ") *");
			}
			else
				System.out.println(partners.getName() + " (" + partners.getCodeName() + ")");
		}
	}
	
	public ArrayList<Suspect> getPartnersList(){
		return Partners;
	}
	
	public void SuggestedPartners(){
		for(Suspect partners : this.getPartnersList()) {
			for(Suspect partnerPartners : partners.getPartnersList()) {
				if ( partnerPartners != this ) {
					boolean exist = false;
					for(Suspect partners2 : this.getPartnersList()) {
						if(partnerPartners == partners2) {
							exist = true;
						}
					}
					if(exist == false)
						suggestedPartners.add(partnerPartners);
				}
				
			}
		}
		
	}	
	public ArrayList<Suspect> getSuggestedPartnersList(){
		return suggestedPartners;
	}
	

}
