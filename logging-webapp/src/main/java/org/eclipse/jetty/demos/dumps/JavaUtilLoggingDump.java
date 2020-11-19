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
import java.util.logging.Handler;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.eclipse.jetty.demos.Util.toDebugString;

public class JavaUtilLoggingDump
{
    public static void dump(PrintWriter out) throws IOException
    {
        LogManager logManager = LogManager.getLogManager();
        out.printf("  java.util.logging - LogManager: %s%n", toDebugString(logManager));

        Logger rootLogger = logManager.getLogger("");
        out.printf("  java.util.logging - root Logger: %s%n", toDebugString(rootLogger));

        for (Handler handler : rootLogger.getHandlers())
        {
            out.printf("  rootLogger.handler - %s%n", toDebugString(handler));
        }
    }
}
