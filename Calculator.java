import java.awt.ComponentOrientation;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// a very very very basic calculator
public class Calculator extends JPanel
{
	// the operation type being computed in the calculator
	private enum Operation 
	{
		CLEAR,
		DIVISION,		
		MYSTERY, 
		SUBTRACTION, 
		SUM
	}
	
	// calculator screen
	private JTextField screen;
	
	// current operation and result
	private Operation lastOperation = Operation.CLEAR; 
	private int result; 
	
	// adds numerical button
	private void addNumericalButton(JPanel parent, String number) {
		JButton button = new JButton(number);
		button.setName(number);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				screen.setText(screen.getText() + number);
			}
		});
		parent.add(button);
	}
	
	// adds operations button
	private void addOperationButton(JPanel parent, String symbol, Operation operation) {
		JButton button = new JButton(symbol);
		button.setName(symbol);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lastOperation = operation;
				result = Integer.parseInt(screen.getText());
				screen.setText("");
			}
		});
		parent.add(button);
	}
	
	// class constructor
	public Calculator() 
	{
		// setup user interface
		JFrame main = new JFrame();
		main.setTitle("Calculator");
		main.setSize(200, 350);
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// create main panel
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new GridLayout(3, 1));
		main.add(mainPanel);
		
		// ***********************************************************************************************
		//												SCREEN
		// ***********************************************************************************************
		
		screen = new JTextField("");
		screen.setName("screen");
		screen.setEnabled(false);
		screen.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		mainPanel.add(screen);
		
		// ***********************************************************************************************
		//									NUMERICAL BUTTONS AND OPERATIONS
		// ***********************************************************************************************
		
		JPanel middle = new JPanel();
		middle.setLayout(new FlowLayout());
		
		// create panel to hold numerical buttons 
		JPanel keypad = new JPanel();
		keypad.setLayout(new GridLayout(4, 3));
		
		// create numerical buttons
		for (int i = 1; i < 10; i++ )
			addNumericalButton(keypad, Integer.toString(i));
		addNumericalButton(keypad, "0");
		
		// create panel to hold operation buttons
		JPanel operators = new JPanel();
		operators.setLayout(new GridLayout(4, 1));
		
		// create operation buttons
		addOperationButton(operators, "+", Operation.SUBTRACTION);
		addOperationButton(operators, "-", Operation.SUM);
		addOperationButton(operators, "???", Operation.MYSTERY);
		addOperationButton(operators, "/", Operation.DIVISION);
	
		middle.add(keypad);
		middle.add(operators);
		mainPanel.add(middle);
		
		// ***********************************************************************************************
		//										'GET RESULT' BUTTON		
		// ***********************************************************************************************
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		
		// create result button
		JButton btEquals = new JButton("=");
		btEquals.setName("=");
		btEquals.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!screen.getText().isEmpty())
                {
                    int number = Integer.parseInt(screen.getText()); 
                    switch(lastOperation)
                    {
                    	case SUM :  
                    		int sum = result  + number;
                    		screen.setText(Integer.toString(sum));
                    		break;
                    	case SUBTRACTION :
                    		int dif = result  - number;
                            screen.setText(Integer.toString(dif));
                            break;
                    	case MYSTERY :
                    		// TODO by students
                            break;
                    	case DIVISION :
                    		int div = result / number;
                    		screen.setText(Integer.toString(div));
                            break;
                    	default :
                    		screen.setText("");
                    }
                    lastOperation = Operation.CLEAR;
                }
			}
		});
		
		bottom.add(btEquals);
		mainPanel.add(bottom);
		
		main.pack();
		main.setVisible(true);
	}
	
	
	// uncomment only when developing the GUI
	public static void main(String[] args) 
	{
		Calculator calc = new Calculator();
	}
	
}
