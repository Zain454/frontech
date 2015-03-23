package tr.com.frontech.servlet;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import tr.com.frontech.service.TimerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Singleton
public class RandomDataServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final static Logger LOGGER = Logger.getLogger(RandomDataServlet.class);

    @Inject
    private TimerService timerService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        LOGGER.info("random datas has responsed.");

        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("rndLong", timerService.getRandLong());
        jsonObject.put("rndStr", timerService.getRandStr());

        out.print(jsonObject);
        out.flush();
    }

}
