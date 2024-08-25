import java.util.ArrayList;

public class Registry {
	
	private ArrayList<Suspect> suspects = new ArrayList<Suspect>(); 
	private ArrayList<Communication> communications = new ArrayList<>();
	
	public void addSuspect(Suspect aSuspect) {
		suspects.add(aSuspect);
	}
	
	public void addCommunication(Communication aCommunication) {
		communications.add(aCommunication);                                 //καταχωρηση επικοινωνιας
		for(Suspect suspect: suspects) {                                    //διατρεχει τους υποπτους εναν εναν , στην συνεχεια
			ArrayList<String> phone = suspect.phones();                     //για καθε εναν διαβαζει τις επαφες του
			for(String y: phone) {                                          // επειτα διατρεχει τις επαφες του
				if (aCommunication.getNumber1().equals(y)) {                //ελενγχος εαν ο πρωτος αριθμος της επικοινωνιας ειναι ιδιος με καποια επαφη του υποπτου
					for(Suspect s :suspects) {                              //εαν η επαφη του υποπτου ειναι ιδιο με το νουμερο της επικοινωνιας τοτε ξαναδιατρεχει τους
						ArrayList<String> pho = s.phones();                 // υποπτους και για καθε εναν τις επαφες του 
						  	for(String o:pho) {                             //μεχρι να βρε8ει σε ποιον ανηκει ο δευτερος αριθμος της επικοινωνιας 
						  		if(aCommunication.getNumber2().equals(o))   // ωστε να καταχωρησουμε στους συναργατες του αρχικου τον αντιστοιχο υποπτο.
						  			suspect.addPartners(s);
						  	}
					}
				}
			}
			
		}
		
	}
	public Suspect getSuspectWithMostPartners() {
		Suspect suspect = null;
		int counter = 0 ;
		for(Suspect s :suspects) {
			if(s.getCountPartners() >= counter) {
				counter = s.getCountPartners();
				suspect = s;
			}
		}
		return suspect;
	}
	
	public void printSuspectsFromCountry(String country) {
		System.out.println("Suspect coming from "+ country);
		for(Suspect s : suspects) {
			if(s.getCountry().equals(country)) {
				System.out.println(s.getName() + "(" + s.getCodeName() + ")" );

			}
		}
	}
	
	public PhoneCall getLongestPhoneCallBetween(String number1,String number2) {
		PhoneCall ph = null;
		int max = 0 ; //Έυρεση μεγαλύτερης κλήσης σε δευτερόλεπτα.
		for(Communication c:communications) {
			if (number1.equals(c.getNumber1()) && (number2.equals(c.number2))){
				if (c.getSec() > max)
					max = c.getSec();
			}
		}
		for(Communication c:communications) {
			if (c.getSec() == max)
				ph = new PhoneCall(number1,number2,c.getDay(),c.getMonth(),c.getYear(),c.getSec());
		}
		return ph;
	}
	
	public ArrayList<SMS> getMessagesBetween(String number1,String number2) {
		ArrayList<SMS> sms = new ArrayList<>();
		SMS s = null ; 
		for(Communication c : communications) {
			if (number1.equals(c.getNumber1()) && (number2.equals(c.number2))) {
				if (c.getText().contains("Bomb")) {
					s = new  SMS(number1,number2,c.getDay(),c.getMonth(),c.getYear(),c.getText());
					sms.add(s);
				}
				if (c.getText().contains("Attack")) {
					s = new  SMS(number1,number2,c.getDay(),c.getMonth(),c.getYear(),c.getText());
					sms.add(s);
				}
				if (c.getText().contains("Explosives")) {
					s = new  SMS(number1,number2,c.getDay(),c.getMonth(),c.getYear(),c.getText());
					sms.add(s);
				}
				if (c.getText().contains("Gun")) {
					s = new  SMS(number1,number2,c.getDay(),c.getMonth(),c.getYear(),c.getText());
					sms.add(s);
				}
			}
		}
		return sms;
		
	}
	
	public Suspect getSuspectforGUI(String name){
		for(Suspect suspect : suspects) {
			if(name.equals(suspect.getName()))
				return suspect;
		}
		return null;
	}
	
	public ArrayList<String> getSuspectNamesInSameCountry(String country,String name){
		ArrayList<String> names = new ArrayList<>();
		for(Suspect sus :suspects) {
			if ((sus.getCountry().equals(country)) && (!sus.getName().equals(name)))
					names.add(sus.getName());
		}
		return names;
	}
	
	public ArrayList<String> getSMSMessage(String number1 , String number2){
		ArrayList<String> smsMessage = new ArrayList<>();
		for(Communication c : communications) {
			if (number1.equals(c.getNumber1()) && (number2.equals(c.getNumber2()))) {
				if (c.getText().contains("Bomb"))
					smsMessage.add(c.getText());					
				else if (c.getText().contains("Attack")) 
					smsMessage.add(c.getText());
				else if (c.getText().contains("Explosives")) 
					smsMessage.add(c.getText());				
				else if (c.getText().contains("Gun")) 
					smsMessage.add(c.getText());				
			}
		}
		return smsMessage;
	}
	
	
	
	

}

