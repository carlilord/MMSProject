package at.jku.commands;

import at.jku.data.DataManager;

import java.awt.image.BufferedImage;

public interface Command {


    public BufferedImage execute(DataManager dm);

}
