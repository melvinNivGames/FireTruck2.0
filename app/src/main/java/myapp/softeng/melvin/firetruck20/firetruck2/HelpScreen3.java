package myapp.softeng.melvin.firetruck20.firetruck2;

/**
 * Created by Melvin on 12/02/2016.
 * 3rd help screen activity
 */
import java.util.List;

import myapp.softeng.melvin.firetruck20.framework.Game;
import myapp.softeng.melvin.firetruck20.framework.Graphics;
import myapp.softeng.melvin.firetruck20.framework.Input.TouchEvent;
import myapp.softeng.melvin.firetruck20.framework.Screen;

public class HelpScreen3 extends Screen {
    public HelpScreen3(Game game) {
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
                if(inBounds(event, 290, 210, 30, 50) ) {
                    game.setScreen(new HelpScreen4(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if(inBounds(event, 10, 210, 30, 50) ) {
                    game.setScreen(new HelpScreen2(game));
                    if (Settings.soundEnabled)
                        Assets.click.play(1);
                    return;
                }
                if (event.x < 64 && event.y > 416) {
                    game.setScreen(new MainMenuScreen(game));
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

    @Override
    public void present(float deltaTime) {
        Graphics g = game.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.helpscreen3, 64, 100);
        g.drawPixmap(Assets.back, 0, 210);
        g.drawPixmap(Assets.next, 290, 210);
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

