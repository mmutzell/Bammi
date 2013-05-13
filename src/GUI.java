import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A graphical user interface for Bammi. The main window
 * that the user interacts with during use.
 * 
 * @authors Martin M�tzell, Sean Wenstr�m
 * @version 2013-05-13
 */
public class GUI {

	private JFrame frame;
	private GameField gameField;
	
	/**
	 * Constructs a new GUI.
	 */
	public GUI(Pie[][] map, int size, int numberOfPlayers) {
		frame = new JFrame("Bammi");
		makeMenuBar();
		gameField = new GameField(map, size, 40);
		frame.add(gameField);
		frame.setVisible(true);
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
		
		//--------------------------------------------------
		//Settings submenu
		
		JMenu settings = makeSettingsMenu();
		gameMenu.add(settings);

		
		
		
		
		/*
		settings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings();
			}
		});
		*/
		
		
		gameMenu.add(settings);
		//--------------------------------------------------
		
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
	 * DEPRECATED
	 * Brings up the settings menu.
	 */
//	private void settings(){
//		//TODO
//	}
	 
	 
	/**
	 * Quits the application.
	 */
	private void quit(){
		System.exit(0);
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
	
	/**
	 * Sets up the "settings" submenu.
	 */
	private JMenu makeSettingsMenu(){
		JMenu settings = new JMenu("Settings");
		
		JMenuItem players = new JMenuItem("Number of players");
		players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings_showNumberOfPlayers(); 
			}
		});
		settings.add(settings_players);
		
		JMenuItem size = new JMenuItem("Map size");
		size.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				settings_showMapSize();
			}
		});
		settings.add(size);
		
		return settings;
	}
	
	/**
	 * Displays a dialog box for entering the new size.
	 */
	private void settings_showMapSize(){
		//TODO
	}
	
	/**
	 * Displays a dialog for entering the new
	 * number of players.
	 */
	private void settings_showNumberOfPlayers(){
		//TODO
	}
}
