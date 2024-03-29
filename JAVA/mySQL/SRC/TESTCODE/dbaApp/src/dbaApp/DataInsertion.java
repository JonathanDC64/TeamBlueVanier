package dbaApp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class DataInsertion extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		setResizable(false);
		setTitle("Data Insertion");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 410, 128);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		StudentIdField = new JTextField();
		StudentIdField.setBounds(10, 25, 120, 20);
		contentPane.add(StudentIdField);
		StudentIdField.setColumns(10);
		
		fnameField = new JTextField();
		fnameField.setColumns(10);
		fnameField.setBounds(140, 25, 120, 20);
		contentPane.add(fnameField);
		
		lnameField = new JTextField();
		lnameField.setColumns(10);
		lnameField.setBounds(270, 25, 120, 20);
		contentPane.add(lnameField);
		
		JLabel lblStudentId = new JLabel("Student Id");
		lblStudentId.setBounds(10, 11, 61, 14);
		contentPane.add(lblStudentId);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(140, 11, 61, 14);
		contentPane.add(lblFirstName);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(270, 11, 61, 14);
		contentPane.add(lblLastName);
		
		JButton btnInsert = new JButton("Insert");
		
		final DBConnector db = new DBConnector();
		db.connect();
		
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String [] values = {StudentIdField.getText(), fnameField.getText(), lnameField.getText()};
				db.insert("student",values);
			}
		});
		btnInsert.setBounds(140, 56, 89, 23);
		contentPane.add(btnInsert);
	}
}
