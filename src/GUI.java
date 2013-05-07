import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A graphical user interface for Bammi. The main window
 * that the usere interacts with during use.
 * 
 * @author Martin Mützell
 * @version 2013-05-07
 */
public class GUI {

	private JFrame frame;
	
	/**
	 * Constructs a new GUI.
	 */
	public GUI() {
		frame = new JFrame("Bammi");
		makeMenuBar();
	}
	
	/**
	 * Creates a menu bar for the window with the menus
	 * "Game" and "Help".
	 */
	private void makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		//The game menu
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenuItem newGame = new JMenuItem("New game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				newGame();
			}
		});
		gameMenu.add(newGame);
		
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
	
	/**
	 * Starts a new game.
	 */
	private void newGame(){
		//TODO
	}
	
	/**
	 * Brings up the settings menu.
	 */
	private void settings(){
		//TODO
	}
	
	/**
	 * Quits the application.
	 */
	private void quit(){
		//TODO
	}
	
	/**
	 * Brings up a new frame, displaying the rules.
	 */
	private void howToPlay(){
		//TODO
	}
	
	/**
	 * Brings up a new frame with information about the game.
	 */
	private void aboutBammi(){
		//TODO
	}
}
