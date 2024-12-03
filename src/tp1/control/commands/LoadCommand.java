package tp1.control.commands;

import java.io.File;
import java.io.IOException;

import tp1.logic.GameModel;
import tp1.view.GameView;
import tp1.view.Messages;

public class LoadCommand extends Command {

    private static final String NAME = Messages.COMMAND_LOAD_NAME;
    private static final String SHORTCUT = Messages.COMMAND_LOAD_SHORTCUT;
    private static final String DETAILS = Messages.COMMAND_LOAD_DETAILS;
    private static final String HELP = Messages.COMMAND_LOAD_HELP;

    private File inputFile;
    private String inputFilePath;

    public LoadCommand() {
        super(NAME, SHORTCUT, DETAILS, HELP);
    }

    public LoadCommand(File inputFile) {
        super(NAME, SHORTCUT, DETAILS, HELP);
        this.inputFile = inputFile;
    }

    @Override
    public void execute(GameModel game, GameView view) {
        // should try call the read file operation
        try {
            game.load(this.inputFile);
        } catch (Exception e) {
            System.err.println("Unable to execute on entered file");
        }
    }

    @Override
    public Command parse(String[] commandWords) {
        try {
            if (commandWords == null || commandWords.length < 2 || commandWords[1] == null) {
                throw new IllegalArgumentException("Invalid command: Missing file name.");
            }
            if (commandWords.length > 2) {
                throw new IllegalArgumentException("Invalid command: Too many arguments.");
            }

            String filePath = "C:\\Users\\finnf\\Desktop\\College\\UCM\\TP1\\LemmingsAssignment2\\2425-Lemmings\\src\\tp1\\logic\\file\\"
                    + commandWords[1];
            File loadFile = new File(filePath);

            return new LoadCommand(loadFile);

        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean showBoard() {
        return true;
    }

}
