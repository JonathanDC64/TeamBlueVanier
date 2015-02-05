package dbaApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DataInsertion extends JFrame {

	private JPanel contentPane;
	private JTextField StudentIdField;
	private JTextField fnameField;
	private JTextField lnameField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DataInsertion frame = new DataInsertion();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public DataInsertion() {
		setTitle("Data Insertion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		StudentIdField = new JTextField();
		StudentIdField.setBounds(90, 140, 120, 20);
		contentPane.add(StudentIdField);
		StudentIdField.setColumns(10);
		
		fnameField = new JTextField();
		fnameField.setColumns(10);
		fnameField.setBounds(220, 140, 120, 20);
		contentPane.add(fnameField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(350, 140, 120, 20);
		contentPane.add(lnameField);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(90, 125, 61, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(220, 125, 61, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(350, 125, 61, 14);
		contentPane.add(lblLastName);
		
		JButton btnInsert = new JButton("Insert");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] values = {StudentIdField.getText(), fnameField.getText(), lnameField.getText()};
				DBConnector.insert("student",values);
			}
		});
		btnInsert.setBounds(234, 182, 89, 23);
		contentPane.add(btnInsert);
	}
}
