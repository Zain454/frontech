package tr.com.frontech.service;

import com.google.inject.ImplementedBy;
import tr.com.frontech.service.impl.RandomDataServiceImpl;

@ImplementedBy(RandomDataServiceImpl.class)
public interface RandomDataService {

    public String callApi();

}
