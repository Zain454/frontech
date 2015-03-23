package tr.com.frontech.module;

import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import org.apache.log4j.Logger;
import tr.com.frontech.service.TimerService;
import tr.com.frontech.service.impl.TimerServiceImpl;
import tr.com.frontech.servlet.RandomDataServlet;
import tr.com.frontech.servlet.UpdaterServlet;

import java.util.Properties;

public class CustomServletModule extends ServletModule {

    private final static Logger LOGGER = Logger.getLogger(CustomServletModule.class);

    @Override
    protected void configureServlets() {
        LOGGER.info("configureServlets has runned.");

        try {
            Properties properties = new Properties();
            properties.load(CustomServletModule.class.getResourceAsStream("/application.properties"));

            Names.bindProperties(binder(), properties);
        } catch (Exception e) {
            binder().addError(e);
            LOGGER.error(e);
        }

        bind(TimerService.class).to(TimerServiceImpl.class).in(Scopes.SINGLETON);

        serve("/random/").with(RandomDataServlet.class);
        serve("/update/").with(UpdaterServlet.class);
    }

}