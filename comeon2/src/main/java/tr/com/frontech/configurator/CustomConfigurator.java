package tr.com.frontech.configurator;

import org.apache.log4j.Logger;
import tr.com.frontech.listener.CustomerGuiceInjectorListener;

import javax.websocket.server.ServerEndpointConfig;

public class CustomConfigurator extends ServerEndpointConfig.Configurator {

    private final static Logger LOGGER = Logger.getLogger(CustomConfigurator.class);

    @Override
    public <T> T getEndpointInstance(Class<T> clazz) throws InstantiationException {
        LOGGER.info("getEndpointInstance has runned.");

        return CustomerGuiceInjectorListener.injector.getInstance(clazz);
    }

}