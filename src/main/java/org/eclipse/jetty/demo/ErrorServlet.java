//
//  ========================================================================
//  Copyright (c) Mort Bay Consulting Pty Ltd and others.
//  ------------------------------------------------------------------------
//  All rights reserved. This program and the accompanying materials
//  are made available under the terms of the Eclipse Public License v1.0
//  and Apache License v2.0 which accompanies this distribution.
//
//      The Eclipse Public License is available at
//      http://www.eclipse.org/legal/epl-v10.html
//
//      The Apache License v2.0 is available at
//      http://www.opensource.org/licenses/apache2.0.php
//
//  You may elect to redistribute this code under either of these licenses.
//  ========================================================================
//

package org.eclipse.jetty.demo;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ErrorServlet extends HttpServlet
{
    private static final Logger LOG = LoggerFactory.getLogger(ErrorServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        if (req.getDispatcherType() != DispatcherType.ERROR)
        {
            // direct access of ErrorServlet is forbidden.
            // you should only be able to get into here
            // from a standard Servlet ERROR dispatch
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        StringBuilder message = new StringBuilder();
        message.append("Error");
        Integer originalStatusCode = (Integer)req.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        if (originalStatusCode != null)
        {
            message.append(" status code [").append(originalStatusCode).append("]");
        }

        String originalMessage = (String)req.getAttribute(RequestDispatcher.ERROR_MESSAGE);
        if (originalMessage != null)
        {
            message.append(" with message [").append(originalMessage).append("]");
        }

        String originalRequestURI = (String)req.getAttribute(RequestDispatcher.ERROR_REQUEST_URI);
        if (originalRequestURI != null)
        {
            message.append(" during request to [").append(originalRequestURI).append("]");
        }

        String originalServletName = (String)req.getAttribute(RequestDispatcher.ERROR_SERVLET_NAME);
        if (originalServletName != null)
        {
            message.append(" during servlet [").append(originalRequestURI).append("]");
        }

        message.append(" from [").append(req.getRemoteAddr()).append("]");

        Throwable cause = (Throwable)req.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
        if (cause != null)
        {
            LOG.warn(message.toString(), cause);
        }
        else
        {
            LOG.warn(message.toString());
        }
    }
}
