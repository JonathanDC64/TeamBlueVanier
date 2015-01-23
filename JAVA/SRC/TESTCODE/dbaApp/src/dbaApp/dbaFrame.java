package dbaApp;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JList;
import javax.swing.JTable;

import java.sql.*;

import java.sql.* ;  // for standard JDBC programs
import java.math.* ;
import javax.swing.JTextPane;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action; // for BigDecimal and BigInteger supp

public class dbaFrame extends JFrame {

	private JPanel contentPane;
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					dbaFrame frame = new dbaFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	JTextPane textPane = new JTextPane();
	/**
	 * Create the frame.
	 */
	public dbaFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 533, 407);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		contentPane.add(textPane, BorderLayout.CENTER);
		
		JButton btnGo = new JButton("Go!");
		btnGo.setAction(action);
		contentPane.add(btnGo, BorderLayout.SOUTH);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
			
			new Thread(new Runnable()
			{

				public void run() {
					String report = DBConnector.report();
					textPane.setText(report);
				}
				
				
			}).start();
			
		}
	}
}
