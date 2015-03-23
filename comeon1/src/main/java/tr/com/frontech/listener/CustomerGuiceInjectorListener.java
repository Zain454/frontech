package tr.com.frontech.listener;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import org.apache.log4j.Logger;
import tr.com.frontech.module.CustomServletModule;

import javax.servlet.annotation.WebListener;

@WebListener
public class CustomerGuiceInjectorListener extends GuiceServletContextListener {

    private final static Logger LOGGER = Logger.getLogger(CustomerGuiceInjectorListener.class);

    public static Injector injector;

    @Override
    protected Injector getInjector() {
        LOGGER.info("getInjector has runned.");

        injector = Guice.createInjector(new CustomServletModule());

        return injector;
    }

}