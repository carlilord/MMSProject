package at.jku.commands;

import java.util.ArrayList;

// ADD YOUR COMMANDS HERE
public class CommandManager {

    private ArrayList<Command> commands;

    public CommandManager() {
        commands = new ArrayList<Command>();
        commands.add(new ScaleImageCommand());
        commands.add(new AddTextCommand());
    }

    public Command[] getCommands() {
        return commands.toArray(new Command[commands.size()]);
    }

}
