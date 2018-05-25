package at.jku.commands;

import at.jku.filters.ConvolutionFilter;
import at.jku.filters.ConvolutionType;
import at.jku.filters.SepiaFilter;

import java.util.ArrayList;

// ADD YOUR COMMANDS HERE
public class CommandManager {

    private ArrayList<Command> commands;

    public CommandManager() {
        commands = new ArrayList<Command>();
        commands.add(new ScaleImageCommand());
        commands.add(new AddTextCommand());
        commands.add(new FilterCommand(new SepiaFilter()));
        commands.add(new AddImageCommand());
        commands.add(new SharpenImage());
        commands.add(new BlurImage());
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.BLUR)));
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.MEAN)));
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.MOTION)));
    }

    public Command[] getCommands() {
        return commands.toArray(new Command[commands.size()]);
    }

}
