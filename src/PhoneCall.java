
public class PhoneCall extends Communication {

	private int sec;
	
	public int getSec() {
		return sec;
	}

	public PhoneCall(String number1, String number2, int day, int month, int year, int sec) {
		super(number1, number2, year, month, day);
		this.sec = sec;
	}
	
	public void printInfo() {
		System.out.println("This phone call has the following info");
		System.out.println("Between " + number1 + " --- " + number2);
		System.out.println("on " + year + "/" + month + "/" + day);
		System.out.println("Duration : " + sec);
	}
	public String getText() {  //override  
		return "";
	}
	
		
}


