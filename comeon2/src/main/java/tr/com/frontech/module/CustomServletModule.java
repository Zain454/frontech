package tr.com.frontech.module;

import com.google.inject.Scopes;
import com.google.inject.name.Names;
import com.google.inject.servlet.ServletModule;
import org.apache.log4j.Logger;
import tr.com.frontech.service.RandomDataService;
import tr.com.frontech.service.TimerService;
import tr.com.frontech.service.impl.RandomDataServiceImpl;
import tr.com.frontech.service.impl.TimerServiceImpl;
import tr.com.frontech.servlet.UpdaterServlet;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

        bind(RandomDataService.class).to(RandomDataServiceImpl.class).in(Scopes.SINGLETON);
        bind(TimerService.class).to(TimerServiceImpl.class).in(Scopes.SINGLETON);

        serve("/update/").with(UpdaterServlet.class);
    }

}