import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GUI extends JFrame{
	private Suspect suspect;
	private Registry registry;
	private ArrayList<String> PhoneCalls = new ArrayList<>();
	private ArrayList<Suspect> Partners = new ArrayList<>();
	
	private JTextField textfield_1_1 ; 
	private JTextField textfield_1_2 ; 
	private JList list_1;
	private JPanel panel_1 = new JPanel();
	
	private JTextField textfield_2_1;
	private JTextArea textArea_2_1;
	private JButton button_2_1;
	private JPanel panel_2 = new JPanel();
	
	private JLabel label_3_1;
	private JTextArea textArea_3_1;
	private JPanel panel_3 = new JPanel();
	
	private JLabel label_4_1;
	private JTextArea textArea_4_1;
	private JPanel panel_4 = new JPanel();
	
	private JTextArea textArea_5_1;
	private JPanel panel_5 = new JPanel();
	
	private JButton button_6;
	
	
	
	
	private JPanel panel = new JPanel();
	public GUI(Suspect asuspect,Registry registry) {
		suspect = asuspect;
		this.registry = registry;
		PhoneCalls = suspect.phones();
		Partners = suspect.getPartnersList();
		//1--------------------------------------------------------------
		
		textfield_1_1 = new JTextField(suspect.getName(),10);
		textfield_1_2 = new JTextField(suspect.getCodeName(),10);
		list_1 = new JList();
		
		DefaultListModel<String> model =new DefaultListModel<>();
		for(String pc : PhoneCalls)
			model.addElement(pc);
		list_1.setModel(model);
				
		panel_1.add(textfield_1_1);
		panel_1.add(textfield_1_2);
		panel_1.add(list_1);
		
		//2-------------------------------------------------------------
		
		textfield_2_1 = new JTextField("insert number",12);
		textArea_2_1 = new JTextArea(15,20);
		button_2_1 = new JButton("Find SMS");
		
		ButtonListener listener = new ButtonListener();
		button_2_1.addActionListener(listener);
		
		panel_2.add(textfield_2_1);
		panel_2.add(textArea_2_1);
		panel_2.add(button_2_1);
		
		//3--------------------------------------------------------------
		
		label_3_1 = new JLabel("Partners");
		textArea_3_1 = new JTextArea(10,20);
		ArrayList<Suspect> s = new ArrayList<>();
		s = suspect.getPartnersList();
		for(Suspect sp : s) {
			textArea_3_1.append(sp.getName() + ", " + sp.getCodeName() + "\n");
		}
		panel_3.add(label_3_1);
		panel_3.add(textArea_3_1);
		
		//4---------------------------------------------------------------
		
		label_4_1 = new JLabel("Suggested partners ----> ");
		textArea_4_1 = new JTextArea(10,20);			
		suspect.SuggestedPartners();
		for(Suspect sp : suspect.getSuggestedPartnersList()) {
			textArea_4_1.append(sp.getName()+ "\n");
		}
		panel_4.add(label_4_1);
		panel_4.add(textArea_4_1);
		
		//5---------------------------------------------------------------
		
		textArea_5_1 = new JTextArea(10,20);
		textArea_5_1.setText("Suspects coming from " + suspect.getCountry());
		ArrayList<String> names = new ArrayList<>();
		names = registry.getSuspectNamesInSameCountry(suspect.getCountry(),suspect.getName());
		for(String n : names) 
			textArea_5_1.append("\n"+ n );
		panel_5.add(textArea_5_1);
		//6---------------------------------------------------------------
		
		button_6 = new JButton("Return To Search Screen");
		ButtonListener2 listener2 = new ButtonListener2() ;
		button_6.addActionListener(listener2);
		
			
		//---------------------------------------------------------------
		panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
		panel.add(panel_1);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(panel_2);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(panel_3);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(panel_4);
		panel.add(Box.createRigidArea(new Dimension(0,5)));
		panel.add(panel_5);
		panel.add(Box.createRigidArea(new Dimension(0,20)));
		panel.add(button_6);
		panel.add(Box.createRigidArea(new Dimension(0,40)));
		this.setContentPane(panel);
		
		this.setVisible(true);
		this.setSize(700,800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Suspect Page");
	}

	
	class ButtonListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			textArea_2_1.setText(" ");
			String text = textfield_2_1.getText();
			ArrayList<String> smsText = new ArrayList<>();
			for(String pc: PhoneCalls) {
				smsText = registry.getSMSMessage(pc, text);
				for(String s : smsText) {
					textArea_2_1.append(s+"\n");
				}
			}
		}
	}
	class ButtonListener2 implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();			
		}
	}
	
}