package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 12/02/2016.
 * The activity that holds the main menu
 */
import java.util.List;

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Input.TouchEvent;
import myapp.softeng.melvin.firetruck20.framework.Screen;

public class MainMenuScreen extends Screen {
    public MainMenuScreen(Game game) {
        super(game);
    }

    public void update(float deltaTime)
    {
        Graphics g = game.getGraphics();
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for(int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);

            if(event.type == TouchEvent.TOUCH_UP) {
                if(inBounds(event, 0, g.getHeight() - 64, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        Assets.click.play(1);
                    }
                }
                //where the game screen appears
                if(inBounds(event, 64, 220, 192, 42) ) {
                    game.setScreen(new GameScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                //highscorescreen will appear
                if(inBounds(event, 64, 220 + 42, 192, 42) ) {
                    game.setScreen(new HighscoreScreen(game));
                    if(Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                //helpscreen will apppear
                if(inBounds(event, 64, 220 + 84, 192, 42) ) {
                    game.setScreen(new HelpScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                //about the team
                if (event.x > 270 && event.y > 425) {
                    game.setScreen(new IteamScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                //system will exit
                if(inBounds(event, 64, 220 + 126, 192, 42) ) {
                    game.setScreen(new ConfirmScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    private boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }

    public void present(float deltaTime) {
        Graphics g = game.getGraphics();

        g.drawPixmap(Assets.mainMenuBackground, 0, 0);
        g.drawPixmap(Assets.mainMenu, 64, 220);
        g.drawPixmap(Assets.about, 270, 425);
        if(Settings.soundEnabled)
            g.drawPixmap(Assets.soundOn, 10, 420);
        else
            g.drawPixmap(Assets.soundOff, 10, 420);
    }

    public void pause()
    {
        Settings.save(game.getFileIO());
    }

    public void resume() {
    }

    public void dispose() {

    }
}
