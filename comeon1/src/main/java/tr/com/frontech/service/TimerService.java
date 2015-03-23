package tr.com.frontech.service;

import com.google.inject.ImplementedBy;
import tr.com.frontech.service.impl.TimerServiceImpl;

import javax.websocket.Session;

@ImplementedBy(TimerServiceImpl.class)
public interface TimerService {

    public void updateScheduled(final Integer period);

    public Long getRandLong();

    public String getRandStr();

}
