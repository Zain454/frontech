package tr.com.frontech.service;

import com.google.inject.ImplementedBy;
import tr.com.frontech.service.impl.TimerServiceImpl;

import javax.websocket.Session;

@ImplementedBy(TimerServiceImpl.class)
public interface TimerService {

    public void createScheduled(final Session session);

    public void cancelScheduled(final Session session);

    public void updateSchedules(final Integer period);

}
