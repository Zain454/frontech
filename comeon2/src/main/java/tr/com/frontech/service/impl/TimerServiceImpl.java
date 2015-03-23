package tr.com.frontech.service.impl;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import org.apache.log4j.Logger;
import tr.com.frontech.service.RandomDataService;
import tr.com.frontech.service.TimerService;

import javax.websocket.Session;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.*;

public class TimerServiceImpl implements TimerService {

    private final static Logger LOGGER = Logger.getLogger(TimerServiceImpl.class);

    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
    private final ConcurrentHashMap<Session, ScheduledFuture<?>> sessionMap = new ConcurrentHashMap<>();

    private Integer period;

    @Inject
    private RandomDataService randomDataService;

    @Inject
    public TimerServiceImpl(@Named("default.period") String period) {
        this.period = Integer.valueOf(period);
    }

    @Override
    public void createScheduled(final Session session) {
        LOGGER.info("C2 period is " + period + " for " + session.getId());

        sessionMap.put(session, timer.scheduleAtFixedRate(new RandomDataRunnable(session), 0, period, TimeUnit.SECONDS));
    }

    @Override
    public void cancelScheduled(final Session session) {
        LOGGER.info("C2 cancelScheduled for " + session.getId());

        sessionMap.get(session).cancel(true);
    }

    @Override
    public void updateSchedules(final Integer period) {
        LOGGER.info("C2 period has updated from " + this.period + " to " + period);

        this.period = period;

        Set<Session> sessions = sessionMap.keySet();

        for (Session session : sessions) {
            sessionMap.get(session).cancel(true);

            LOGGER.info("C2 period has updated for " + session.getId() + " to " + this.period);

            sessionMap.put(session, timer.scheduleAtFixedRate(new RandomDataRunnable(session), 0, this.period, TimeUnit.SECONDS));
        }
    }

    private class RandomDataRunnable implements Runnable {

        private final Session session;

        public RandomDataRunnable(final Session session) {
            this.session = session;
        }

        @Override
        public void run() {
            try {
                LOGGER.info("run has runned for " + session.getId());

                session.getBasicRemote().sendText(randomDataService.callApi());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
