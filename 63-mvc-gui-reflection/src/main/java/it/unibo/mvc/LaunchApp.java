package it.unibo.mvc;

import java.lang.reflect.Constructor;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberSwingView;
import it.unibo.mvc.view.DrawNumberViewImpl;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws Exception{
        
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        /*app.addView(new DrawNumberViewImpl());
        app.addView(new DrawNumberSwingView());
        */
        final Class<?> view1 = Class.forName("it.unibo.mvc.view.DrawNumberSwingView");
        final Class<?> view2 = Class.forName("it.unibo.mvc.view.DrawNumberViewImpl");
        final Constructor<?> c1 = view1.getConstructor();
        final Constructor<?> c2 = view2.getConstructor();
        final Object v1 = c1.newInstance();
        final Object v2 = c2.newInstance();
        for(int i = 0; i < 3; i++){
            app.addView((DrawNumberView)c1.newInstance());
            app.addView((DrawNumberView)c2.newInstance());
        }

    }
}
