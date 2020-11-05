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
  JButton restartButton;

  Font biggerText;

  //create an array for the words in the game
  String[] words = {"hello" , "zebra" , "cut" , "computer"};
  //random word generator
  final Random RANDOM = new Random();
  
  //create random word
  int randIndex = RANDOM.nextInt(4);
  String randWord = words[randIndex];
  
 
  //store the word in array to see users progress 
  char[] letter = randWord.toCharArray();
  int tries = 7;
  //create an array that stores the letters that were typed in
  char[] enteredLetters = new char[randWord.length()];
  //create a variable that will change everytime a letter is not guessed
  boolean correct = true;
  
  


  
   

  


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
    guessLabel.setBounds(200,150,100,20);
    wordLabel.setBounds(300,180,400,20);
    triesLabel.setBounds(200,220,400,20);
    triesRemainingLabel.setBounds(280,220,100,20);

    //add labels to main panel 
    mainPanel.add(guessLabel);
    mainPanel.add(wordLabel);
    mainPanel.add(triesLabel);
    mainPanel.add(triesRemainingLabel);
    //initialize the text text field
    letterInput = new JTextField();
    //set location and size of text field
    letterInput.setBounds(300,150,100,20);
    //add text field to the main panel
    mainPanel.add(letterInput);
    //initialize the buttons
    submitButton = new JButton("Submit");
    restartButton = new JButton("Restart");
    //set location and size of the buttons
    submitButton.setBounds(420,150,100,20);
    restartButton.setBounds(550,150,100,20);
    //add action listener to the buttons
    submitButton.addActionListener(this);
    restartButton.addActionListener(this);
    //add buttons to main panel
    mainPanel.add(submitButton);
    mainPanel.add(restartButton);
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
      correct = false;
      for(int i = 0; i < randWord.length(); i++){
        if(letter[i] == letterGuess){
          enteredLetters[i] = letterGuess; 
          correct = true;
          //convert char array into String
          String letter = String.valueOf(enteredLetters);
          wordLabel.setText("" + letter);
          
        }
      }

      //convert char array into String
      String letters = String.valueOf(enteredLetters);
       if(letters.equals(randWord)){
        wordLabel.setText("You Win! The word was: " + randWord);
       }
       //if user guesses a letter
       if(correct == true){
        triesRemainingLabel.setText("" + tries);
      }
       //if user does not guess a letter in the word 
       if(correct != true){
        tries--;
        triesRemainingLabel.setText("" + tries);
      }
       //if tries equal 0 then game is over
       if(tries == 0){
        wordLabel.setText("You Lose! To try again press Restart");
      }
    }
    if(command.equals("Restart")){
      Random RANDOM = new Random();
      //create new word
      randIndex = RANDOM.nextInt(4);
      //store the word in array to see users progress 
      randWord = words[randIndex];
      letter = randWord.toCharArray();
      //change the word output
      enteredLetters = new char[randWord.length()];
      for(int i = 0; i < randWord.length(); i++){
       enteredLetters[i] = '_';
      //make font bigger
      biggerText = new Font("arial" , Font.PLAIN, 19);
      wordLabel.setFont(biggerText);
      }
      //store the word in array to see users progress 
      randWord = words[randIndex];
      letter = randWord.toCharArray();
      //reset everything to default
      letterInput.setText("");
      wordLabel.setText("");
      triesRemainingLabel.setText("" );
      tries = 7;
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
