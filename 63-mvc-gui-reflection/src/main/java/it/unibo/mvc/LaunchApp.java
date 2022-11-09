package it.unibo.mvc;

import java.lang.reflect.Constructor;
import java.util.List;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;

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
        for ( String type : List.of("ViewImpl", "SwingView")) {
            final Class<?> view = Class.forName("it.unibo.mvc.view.DrawNumber" + type);
            final Constructor<?> cns = view.getConstructor();
            for(int i = 0; i < 3; i++){
                final Object viewObj = cns.newInstance();
                try{
                    app.addView((DrawNumberView)viewObj);
                }catch(IllegalStateException e){
                    System.out.println("Impossible to cast viewObj to DrawNumberView" + e);
                }
            }
        }
    }
}
