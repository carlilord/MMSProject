package at.jku.commands;

import at.jku.filters.*;

import java.util.ArrayList;

// ADD YOUR COMMANDS HERE
public class CommandManager {

    private ArrayList<Command> commands;

    public CommandManager() {
        //Adding Commands
        commands = new ArrayList<Command>();
        commands.add(new ScaleImageCommand());
        commands.add(new AddTextCommand());
        commands.add(new FilterCommand(new SepiaFilter()));
        commands.add(new AddImageCommand());
        commands.add(new SharpenImage());
        commands.add(new AddFrameCommand());
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.BLUR)));
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.MEAN)));
        commands.add(new FilterCommand(new ConvolutionFilter(ConvolutionType.MOTION)));
        commands.add(new FilterCommand(new GreyScaleFilter()));
        commands.add(new FilterCommand(new GammaCorrectionFilter()));
        commands.add(new FilterCommand(new RGBFilter()));
    }

    public Command[] getCommands() {
        return commands.toArray(new Command[commands.size()]);
    }

}
