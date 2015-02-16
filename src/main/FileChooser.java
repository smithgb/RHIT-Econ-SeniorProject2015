package main;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class FileChooser extends JFrame {

	private static final long serialVersionUID = 1L;
	public File selectedFile;
	private JFileChooser chooser;

	public FileChooser(){
		super("File Chooser Test Frame");
		setSize(1, 1);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.chooser = new JFileChooser();
	}
	
	public void launchFileChooser(){
		chooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        chooser.setMultiSelectionEnabled(false);
        int option = chooser.showOpenDialog(FileChooser.this);
        if (option == JFileChooser.APPROVE_OPTION)
          this.selectedFile = chooser.getSelectedFile();
	}
	
	public File getSelectedFile(){
		return(this.selectedFile);
	}
}
