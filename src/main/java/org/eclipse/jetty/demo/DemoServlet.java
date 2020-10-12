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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException
    {
        LOG.info("This is from the DemoServlet.doGet()");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");

        PrintWriter out = resp.getWriter();
        out.println("in doGet()");
        out.printf("The logger is a %s%n", LOG.getClass().getName());
    }
}
