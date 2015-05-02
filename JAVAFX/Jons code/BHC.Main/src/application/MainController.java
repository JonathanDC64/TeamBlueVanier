package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainController implements Initializable{
	@FXML
	private TextField usernameField;
	@FXML
	private TextField passwordField;
	@FXML
	private CheckBox rememberPasswordCheckbox;
	
	public void Login(ActionEvent event) throws IOException {
		
		/*String url = System.getenv("SystemDrive") + "/database/BHCDB/";
		String fileName = "login.prop";
		Path target = FileSystems.getDefault().getPath(url, fileName);
		if(!Files.exists(target, LinkOption.NOFOLLOW_LINKS))
		{
			saveInfo();
		}
		else
		{
			File infoFile = new File(url + fileName);
			Scanner s = new Scanner(infoFile);
			if(		CipherUtils.decrypt(s.nextLine()).equals(usernameField.getText()) &&
					CipherUtils.decrypt(s.nextLine()).equals(passwordField.getText()))
			{
				saveInfo();
				s.close();
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Wrong username or password.");
				s.close();
				return;
			}
		}*/
		
	
		
		
		
		Parent layout_parent = FXMLLoader.load(getClass().getResource("System.fxml"));
		Scene layout_scene = new Scene(layout_parent);
		Stage layout_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		
		
		

		
		layout_stage.setScene(layout_scene);
		
		
		
		
		
		layout_stage.show();
		layout_stage.setResizable(true);
		layout_stage.setMinWidth(960);
		layout_stage.setMinHeight(540);
		Rectangle2D Bounds = Screen.getPrimary().getVisualBounds();
		layout_stage.setX((Bounds.getWidth() - layout_stage.getWidth()) / 2); 
		layout_stage.setY((Bounds.getHeight() - layout_stage.getHeight()) / 4);
		
/*		AnchorPane mainPane = (AnchorPane) layout_scene.lookup("#mainPane");
		
		final int size = 50;
		ProgressIndicator progressIndicator = new ProgressIndicator();
		progressIndicator.setPrefWidth(size);
		progressIndicator.setPrefHeight(size);
		progressIndicator.setLayoutX((layout_stage.getWidth()/2) - (size/2));
		progressIndicator.setLayoutY((layout_stage.getHeight()/2) - (size/2));
		mainPane.getChildren().add(progressIndicator);*/
	}
	
/*	private void saveInfo()
	{
		try {
			String url = System.getenv("SystemDrive") + "/database/BHCDB/";
			String fileName = "login.prop";
			File infoFile = new File(url + fileName);
			PrintWriter p = new PrintWriter(infoFile);
			if(rememberPasswordCheckbox.isSelected())
			{
				p.write("1\n" + CipherUtils.encrypt(usernameField.getText()) + "\n" + CipherUtils.encrypt(passwordField.getText()));
				
			}
			else
			{
				p.write("0");
			}
			p.close();
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}*/
	
	//--------------------------------------------------
	/* Method requested by eclipse in order to compile */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) 
	{
		/*try {

			String url = System.getenv("SystemDrive") + "/database/BHCDB/";
			String fileName = "login.prop";
			Path target = FileSystems.getDefault().getPath(url, fileName);
			if(Files.exists(target, LinkOption.NOFOLLOW_LINKS))
			{
				File infoFile = new File(url + fileName);
				Scanner s = new Scanner(infoFile);
				if(s.nextLine().equals("1"))
				{
					usernameField.setText(CipherUtils.decrypt(s.nextLine()));
					passwordField.setText(CipherUtils.decrypt(s.nextLine()));
				}
				s.close();
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
	}
}
