package tr.com.frontech.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import tr.com.frontech.service.TimerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class UpdaterServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(UpdaterServlet.class);

    @Inject
    private TimerService timerService;

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer period = Integer.valueOf(request.getParameter("period"));

        timerService.updateScheduled(period);

        LOGGER.info("Period has changed to " + period);

        response.getWriter().write("Period has changed to " + period);
        response.getWriter().flush();
    }

}