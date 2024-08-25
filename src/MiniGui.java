import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MiniGui extends JFrame{

	private Registry registry;
	
	private JPanel panel;
	private JTextField textField;
	private JButton button;
	
	public MiniGui(Registry registry) {
		this.registry = registry;
		
		panel = new JPanel();
		textField = new JTextField("Please enter suspect's name");
		button = new JButton("Find");
		
		panel.add(textField);
		panel.add(button);
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		
		this.setContentPane(panel);
		
		this.setSize(400,150);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setTitle("Find Suspect");
	}
	
	class ButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			String text = textField.getText();
			if(!text.equals("John Dow")&&(!text.equals("Danny Rust"))&&(!text.equals("Bob Robson"))&&(!text.equals("John Papas"))) {
				JOptionPane.showMessageDialog(null,"Suspect " + text + " Not Found", "Message", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				new GUI(registry.getSuspectforGUI(text),registry);
				textField.setText("Please enter suspect's name");
			}
				
			
			
			
			
			
		}
		
	}
	
}
