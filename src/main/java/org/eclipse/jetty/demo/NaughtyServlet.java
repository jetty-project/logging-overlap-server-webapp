package org.eclipse.jetty.demo;

import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class NaughtyServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        // Intentionally cause an error 500.
        throw new UnsupportedCharsetException("BAD");
    }
}
