package myapp.softeng.melvin.firetruck20.framework;

/**
 * Created by Melvin on 10/02/2016.
 */
public interface Game
{
    public Input getInput();

    public FileIO getFileIO();

    public Graphics getGraphics();

    public Audio getAudio();

    public void setScreen(Screen screen);

    public Screen getCurrentScreen();

    public Screen getStartScreen();
}
