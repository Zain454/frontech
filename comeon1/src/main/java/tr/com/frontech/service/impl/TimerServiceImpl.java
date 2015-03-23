package tr.com.frontech.service.impl;

import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import tr.com.frontech.service.TimerService;

import javax.websocket.Session;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Singleton
public class TimerServiceImpl implements TimerService {

    private final static Logger LOGGER = Logger.getLogger(TimerServiceImpl.class);
    private static final Random random = new Random();

    private final ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);;

    private Long randLong;
    private String randStr;
    private Integer period = 8;
    private ScheduledFuture<?> scheduledFuture;
    private RandomRunnable randomRunnable = new RandomRunnable();

    public TimerServiceImpl() {
        this.scheduledFuture = timer.scheduleAtFixedRate(randomRunnable, 0, period, TimeUnit.SECONDS);
    }

    @Override
    public void updateScheduled(final Integer period) {
        LOGGER.info("C1 period has updated from " + this.period + " to " + period);

        this.period = period;
        this.randomRunnable = new RandomRunnable();
        this.scheduledFuture.cancel(true);
        this.scheduledFuture = timer.scheduleAtFixedRate(randomRunnable, 0, period, TimeUnit.SECONDS);
    }

    private class RandomRunnable implements Runnable {
        @Override
        public void run() {
            randLong = random.nextLong();
            randStr = String.valueOf(UUID.randomUUID().toString());
        }
    }

    @Override
    public Long getRandLong() {
        return randLong;
    }

    @Override
    public String getRandStr() {
        return randStr;
    }

}
