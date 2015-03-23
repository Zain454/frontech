package tr.com.frontech.websocket;

import com.google.inject.Inject;
import org.apache.log4j.Logger;
import tr.com.frontech.configurator.CustomConfigurator;
import tr.com.frontech.service.TimerService;

import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/simulate/", configurator = CustomConfigurator.class)
public class SimulateWebSocket {

    private final static Logger LOGGER = Logger.getLogger(SimulateWebSocket.class);

    @Inject
    private TimerService timerService;

    @OnOpen
    public void onOpen(final Session session) {
        LOGGER.info("socket connection has opened for " + session.getId());

        timerService.createScheduled(session);
    }

    @OnClose
    public void onClose(final Session session) {
        LOGGER.info("socket connection has closed for " + session.getId());

        timerService.cancelScheduled(session);
    }

}
