package myapp.softeng.melvin.firetruck20.framework.impl;

/**
 * Created by Melvin on 12/02/2016.
 */
import java.util.List;

import android.view.View.OnTouchListener;

import myapp.softeng.melvin.firetruck20.framework.Input.TouchEvent;

public interface TouchHandler extends OnTouchListener {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}

