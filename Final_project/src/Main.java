import View.CommandParserView;


/**
 * The Main class serves as the entry point for the banking application. It initializes
 * the CommandParserView, which handles all user interactions through the console.
 * This class follows the MVC architecture, with the Main class serving as part of the
 * controller layer that orchestrates the flow between the view and the model.
 */
public class Main {
  public static void main(String[] args) {
    CommandParserView commandParserView = new CommandParserView();
    commandParserView.parseCommand(args);
  }
}



