package tp1.control.commands;

import java.io.File;
import java.io.IOException;

import tp1.exceptions.CommandExecuteException;
import tp1.exceptions.CommandParseException;
import tp1.exceptions.GameModelException;
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
    public void execute(GameModel game, GameView view) throws CommandExecuteException {
        try {
            game.load(this.inputFile);
        } catch (Exception e) {
            throw new CommandExecuteException("Unexpected error occurred while loading the file.", e);
        }
    }

    @Override
    public Command parse(String[] commandWords) throws CommandParseException {
        try {
            if (commandWords == null || commandWords.length < 2 || commandWords[1] == null) {
                throw new CommandParseException("Invalid command: Missing file name.");
            }
            if (commandWords.length > 2) {
                return null;
                // throw new CommandParseException("Invalid command: Too many arguments.");
            }

            String filePath = "C:\\Users\\finnf\\Desktop\\College\\UCM\\TP1\\LemmingsAssignment2\\2425-Lemmings\\src\\tp1\\logic\\file\\"
                    + commandWords[1];
            File loadFile = new File(filePath);

            if (!loadFile.exists()) {
                throw new CommandParseException("The specified file does not exist: " + filePath);
            }

            return new LoadCommand(loadFile);

        } catch (CommandParseException e) {
            throw e;
        } catch (Exception e) {
            throw new CommandParseException("Unexpected error while parsing load command.", e);
        }
    }

    @Override
    public boolean showBoard() {
        return true;
    }

}
