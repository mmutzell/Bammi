import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

/**
 * A graphical user interface for Bammi. The main window
 * that the user interacts with during use.
 * 
 * @authors Martin M�tzell, Sean Wenstr�m
 * @version 2013-05-13
 */
public class GUI extends JFrame{
	
	private static final String HOW_TO_PLAY = "Turn the entire playing field to your colour to win!" +
			"\n\nClick a neutral pie to claim it, or one of your own pies to add a slice to it." +
			"\nAdding a slice to a full pie will cause it to explode, moving one slice to each of its " +
			"neighbours and turning them to your colour. These explosions can chain, making it possible to " +
			"claim many pies by adding a single slice.";
	private static final String ABOUT_BAMMI = "This is our implementation of Bammi, done as a programming project " +
			"for the Introductory Course to Computer Science, DD1339, at KTH in Sweden. " +
			"\n\nThis is based on the original implementation of Bammi, which can be found " +
			"on \nwww.bammi.com" +
			""; 
	
	private static final int PIE_SIZE = 60; //pieSize to send to gameField
	private static final int DFLT_SIZE = 10; //default size of map
	
	private Container pane;
	private GameField gameField;
	private JLabel turn;
	private JLabel[] score;
	
	private ArrayList<Player> players;
	private Color[] colors = new Color[] {null, null, Color.GREEN, Color.YELLOW, Color.BLACK};
	
	private int size;
	
	
	/**
	 * Constructs a new GUI.
	 */
	public GUI() {
		super("Bammi");
		 
		size = DFLT_SIZE; //
		players = new ArrayList<Player>();
		players.add(new User(Color.BLUE));
		players.add(new User(Color.RED));
		Game.setPlayers(players);
				
		makeMenuBar();
		pane = getContentPane();
		turn = new JLabel();
		turn.setBackground(Color.white);
		turn.setText("Take over all Pies!");
		pane.add(turn, BorderLayout.NORTH);
		gameField = new GameField(Init.generate(size, this), size, PIE_SIZE);
		pane.add(gameField, BorderLayout.CENTER);
		score = new JLabel[players.size()];
		for(int i=0; i<score.length; i++){
			score[i] = new JLabel();
			System.out.println("s");
			score[i].setBackground(Color.white);
			pane.add(score[i], BorderLayout.SOUTH);
		}
		updateText();
		pack();
		setVisible(true);
	}
	
	/**
	 * Creates a menu bar for the window with the menus
	 * "Game" and "Help".
	 */
	private void makeMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		//The game menu
		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);
		
		JMenuItem newGame = new JMenuItem("New game");
		newGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Game.newGame();
			}
		});
		gameMenu.add(newGame);
		
		//Settings submenu
		
		JMenu settings = makeSettingsMenu();
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
	
	public void updateText(){
		turn.setForeground(Game.getCurrentPlayer().getColor());
		int size = score.length;
		for(int i=0; i<size; i++){
			score[i].setForeground(players.get(i).getColor());
			score[i].setText(((Integer)(players.get(i).getNumberOfPies())).toString());
		}
	}
	
	/**
	 * Starts a new game.
	 */
	public void newGame(){
		pane.remove(gameField);
		gameField = new GameField(Init.generate(size, this), size, PIE_SIZE);
		pane.add(gameField);
		for(JLabel s : score){
			pane.remove(s);
		}
		score = new JLabel[players.size()];
		for(int j=0; j<score.length; j++){
			score[j] = new JLabel();
			score[j].setBackground(Color.white);
			pane.add(score[j], BorderLayout.SOUTH);
		}
		updateText();
		setVisible(true);
	}
	 
	 
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
		JOptionPane.showMessageDialog(null, HOW_TO_PLAY);
	}
	
	/**
	 * Brings up a new frame with information about the game.
	 */
	private void aboutBammi(){
		JOptionPane.showMessageDialog(null, ABOUT_BAMMI);
	}
	
	/**
	 * Sets up the "settings" submenu.
	 */
	private JMenu makeSettingsMenu(){
		JMenu settings = new JMenu("Settings");
		
		JMenuItem players = new JMenuItem("Add player");
		players.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				settings_addPlayer(); 
			}
		});
		settings.add(players);
		
		JMenuItem remplayer = new JMenuItem("Remove player");
		remplayer.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				settings_remPlayer();
			}
		});
		settings.add(remplayer);
		
		JMenuItem size = new JMenuItem("Map size");
		size.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				settings_newSize();
			}
		});
		settings.add(size);
		
		return settings;
	}
	
	/**
	 * Displays a dialog box for entering the new size.
	 */
	private void settings_newSize(){
		try{
			int newSize = Integer.parseInt(JOptionPane.showInputDialog("Please enter the new map size.").toString());
			if(newSize < 2){
				JOptionPane.showMessageDialog(null, "Please enter a number greater than 1.");
				return;
			}
			this.size = newSize;
			Game.newGame();
		}catch(NullPointerException e){	//window closed, do nothing
		}catch(NumberFormatException e){
			JOptionPane.showMessageDialog(null, "Please enter a valid number.");
		}

	}
	
	/**
	 * Displays a dialog for entering the new player.
	 */
	private void settings_addPlayer(){
		int size = colors.length;
		for(Color c : colors){
			if(c == null) size--;
		}
		Color[] options = new Color[size];
		int actualIndex = 0;
		for(Color c : colors){
			if(c != null){
				options[actualIndex] = c;
				actualIndex++;
			}
		}
		if(options.length == 0){
			JOptionPane.showMessageDialog(this, "No more players left to add.");
			return;
		}
		Color chosenColor = options[JOptionPane.showOptionDialog(this, "Please select a color:", "New player" , JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, options, options[0])];
		if(chosenColor != null) players.add(new User(chosenColor));
		//nullify chosen color in main array
		for(int i = 0; i < colors.length; i++){
			if(colors[i] == chosenColor){
				colors[i] = null;
				break;
			}
		}
		
		
		for(JLabel s : score){
			pane.remove(s);
		}
		score = new JLabel[players.size()];
		for(int i=0; i<score.length; i++){
			score[i] = new JLabel();
			score[i].setBackground(Color.white);
			pane.add(score[i], BorderLayout.SOUTH);
		}
		Game.newGame();
		updateText();
	}
	
	private void settings_remPlayer(){
		Color[] c = new Color[players.size()];
		int i = 0;
		for(Player p : players){
			c[i] = p.getColor();
			i++;
		}
		
		if(players.size() == 1){
			JOptionPane.showMessageDialog(this, "You cannot remove the last player.\nPlease add another player first.");
			return;
		}
		
		Color chosenColor = c[JOptionPane.showOptionDialog(this, "Please select a player to remove:", "Remove player" , JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, c, c[0])];
		for(i = 0; i<players.size(); i++){
			if(players.get(i).getColor() == chosenColor) players.remove(i);
		}
		
		//place color back as choosable
		for(i = 0; i < colors.length; i++){
			if(colors[i] == null){
				colors[i] = chosenColor;
				break;
			}
		}

		for(JLabel s : score){
			pane.remove(s);
		}
		score = new JLabel[players.size()];
		for(int j=0; j<score.length; j++){
			score[j] = new JLabel();
			score[j].setBackground(Color.white);
			pane.add(score[j], BorderLayout.SOUTH);
		}
		
		Game.newGame();
		updateText();
		
	}
	
	@Override
	public void repaint(){
		super.repaint();
		updateText();
	}

	public GameField getGameField(){
		return gameField;
	}
}
