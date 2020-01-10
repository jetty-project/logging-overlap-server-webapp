package org.eclipse.jetty.demo;

import java.io.IOException;
import java.net.URI;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorServlet extends HttpServlet
{
    private static final Logger LOG = LoggerFactory.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        if (req.getDispatcherType() != DispatcherType.ERROR)
        {
            // direct access of ErrorServlet is forbidden.
            // you should only be able to get into here
            // from a standard Servlet ERROR dispatch
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        URI uri = (URI)req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);

        Throwable cause = (Throwable)req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (cause != null)
        {
            LOG.warn("Error during " + uri, cause);
        }
        else
        {
            LOG.warn("Error during " + uri);
        }
    }
}
