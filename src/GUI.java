import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class GUI {

	private JFrame frame;
	
	public GUI() {
		frame = new JFrame("Bammi");
		makeMenuBar();
	}
	
	private void makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//The game menu
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenuItem restart = new JMenuItem("New game");
		restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				restart();
			}
		});
		gameMenu.add(restart);
		
		JMenuItem settings = new JMenuItem("Settings");
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings();
			}
		});
		gameMenu.add(settings);
		
		JMenuItem quit = new JMenuItem("Quit");
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				quit();
			}
		});
		gameMenu.add(quit);
		
		//The help menu
		JMenu helpMenu = new JMenu("Help");
		menuBar.add(helpMenu);
		
		JMenuItem howToPlay = new JMenuItem("How to play");
		howToPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				howToPlay();
			}
		});
		helpMenu.add(howToPlay);
		
		JMenuItem aboutBammi = new JMenuItem("About bammi");
		aboutBammi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				aboutBammi();
			}
		});
		helpMenu.add(aboutBammi);
	}
	
	private void restart(){
		//TODO
	}
	
	private void settings(){
		//TODO
	}
	
	private void quit(){
		//TODO
	}
	
	private void howToPlay(){
		//TODO
	}
	
	private void aboutBammi(){
		//TODO
	}
}
