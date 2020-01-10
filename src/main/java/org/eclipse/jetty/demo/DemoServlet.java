package org.eclipse.jetty.demo;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DemoServlet extends HttpServlet
{
    private static final Logger LOG = LoggerFactory.getLogger(DemoServlet.class);

    @Override
    public void init() throws ServletException
    {
        super.init();
        LOG.info("Initializing the WebApp");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        LOG.info("This is from the DemoServlet.doGet()");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("in doGet()");
        out.printf("The logger is a %s%n", LOG.getClass().getName());
    }
}
