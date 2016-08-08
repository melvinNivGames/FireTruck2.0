package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 12/02/2016.
 * The activity that shows the developers
 */
import java.util.List;

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Input.TouchEvent;
import myapp.softeng.melvin.firetruck20.framework.Screen;

public class IteamScreen extends Screen {
    public IteamScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        int len = touchEvents.size();
        for (int i = 0; i < len; i++) {
            TouchEvent event = touchEvents.get(i);
            if (event.type == TouchEvent.TOUCH_UP) {
                if (event.x < 64 && event.y > 420) {
                    game.setScreen(new MainMenuScreen(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.iteam, 113, 15);
        g.drawPixmap(Assets.version, 63, 105);
        g.drawPixmap(Assets.exit, 10, 425);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}

