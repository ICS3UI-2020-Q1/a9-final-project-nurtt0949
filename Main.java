import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
public class Main implements Runnable, ActionListener{

  // Class Variables  
  JPanel mainPanel;

  JLabel guessLabel;
  JLabel wordLabel;
  JLabel triesRemainingLabel;
  JLabel triesLabel;

  JTextField letterInput;

  JButton submitButton;

  Font biggerText;

  //create an array for the words in the game
  String[] words = {"hello" , "zebra" , "cut" , "computer"};
  //random word generator
  final Random RANDOM = new Random();
  //create random word
  int randIndex = RANDOM.nextInt(4);
  String randWord = words[randIndex];
  
  //amount of tries the user gets
  final int MAX_TRIES = 0;
  //store the word in array to see users progress 
  char[] letter = randWord.toCharArray();
  int tries = 7;
  //create an array that stores the letters that were typed in
  char[] enteredLetters = new char[randWord.length()];
  


  
   

  


  // Method to assemble our GUI
  public void run(){
    // Creats a JFrame that is 800 pixels by 600 pixels, and closes when you click on the X
    JFrame frame = new JFrame("Hangman");
    // Makes the X button close the program
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // makes the windows 800 pixel wide by 600 pixels tall
    frame.setSize(800,600);
    // shows the window
    frame.setVisible(true);
    // initialize the main JPanel
    mainPanel = new JPanel();
    // turn on the manual layouts
    mainPanel.setLayout(null);
    //create the labels to tell user what to enter and to show the amount of letters they guessed in a word
    guessLabel = new JLabel("Your Guess:");
    wordLabel = new JLabel("");
    triesRemainingLabel = new JLabel();
    triesLabel = new JLabel("Tries Left:");
    //set the location and size of the labels
    guessLabel.setBounds(50,50,100,20);
    wordLabel.setBounds(150,100,400,20);
    triesLabel.setBounds(50,150,400,20);
    triesRemainingLabel.setBounds(125,150,100,20);

    //add labels to main panel 
    mainPanel.add(guessLabel);
    mainPanel.add(wordLabel);
    mainPanel.add(triesLabel);
    mainPanel.add(triesRemainingLabel);
    //initialize the text text field
    letterInput = new JTextField();
    //set location and size of text field
    letterInput.setBounds(150,50,100,20);
    //add text field to the main panel
    mainPanel.add(letterInput);
    //initialize the submit button
    submitButton = new JButton("Submit");
    //set location and size of the button
    submitButton.setBounds(270,50,100,20);
    //add action listener to the button
    submitButton.addActionListener(this);
    //add button to main panel
    mainPanel.add(submitButton);
    //add main panel to frame
    frame.add(mainPanel);
    for(int i = 0; i < randWord.length(); i++){
      enteredLetters[i] = '_';
    //make font bigger
    biggerText = new Font("arial" , Font.PLAIN, 19);
    wordLabel.setFont(biggerText);
     
    }

  }

   // method called when a button is pressed
   public void actionPerformed(ActionEvent e){
    // get the command from the action
    String command = e.getActionCommand();
    if(command.equals("Submit")){
      //get text from input box
      String firstText = letterInput.getText();
      //check if letter is in the word
      char letterGuess = firstText.charAt(0);
      
      for(int i = 0; i < randWord.length(); i++){
        if(letter[i] == letterGuess){
          enteredLetters[i] = letterGuess; 
          //convert char array into String
          String letter = String.valueOf(enteredLetters);
          wordLabel.setText("" + letter);
        } 
        
      } 
      //convert char array into String
      String letters = String.valueOf(enteredLetters);
       if (letters.equals(randWord)){
        wordLabel.setText("You Win! The word was: " + randWord);
       }
    } 
   }
    
      
     
   

  // Main method to start our program
  public static void main(String[] args){
    
    // Creates an instance of our program
    Main gui = new Main();
    // Lets the computer know to start it in the event thread
    SwingUtilities.invokeLater(gui);
  }
}
