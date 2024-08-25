
public class SMS extends Communication {

	private String text;
	
	public SMS(String number1, String number2, int day, int month, int year,String text) {
		super(number1, number2, year, month, day);
		this.text = text;
	}
	
	public void printInfo() {
		System.out.println("This SMS has the following info");
		System.out.println("between " + number1 + " --- " + number2);
		System.out.println("on " + year + "/" + month + "/" + day);
		System.out.println("Text: " + text);
	}
	
	
	public String getText() {
		return text;
	}
	

	@Override
	public int getSec() {
		return 0;
	}

}
