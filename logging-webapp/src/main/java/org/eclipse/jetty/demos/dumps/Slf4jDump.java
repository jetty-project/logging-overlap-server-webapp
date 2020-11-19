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

package org.eclipse.jetty.demos.dumps;

import java.io.IOException;
import java.io.PrintWriter;

import org.slf4j.ILoggerFactory;
import org.slf4j.Logger;

import static org.eclipse.jetty.demos.Util.toDebugString;
import static org.slf4j.LoggerFactory.getILoggerFactory;
import static org.slf4j.LoggerFactory.getLogger;

public class Slf4jDump
{
    public static void dump(PrintWriter out) throws IOException
    {
        ILoggerFactory loggerFactory = getILoggerFactory();
        out.printf("  slf4j ILoggerFactory: %s%n", toDebugString(loggerFactory));
        Logger rootLogger = getLogger("");
        out.printf("  slf4j root Logger: %s%n", toDebugString(rootLogger));
    }
}
