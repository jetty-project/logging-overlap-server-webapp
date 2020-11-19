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

package org.eclipse.jetty.demos.lib.slf4j;

import java.util.EnumSet;
import java.util.Set;
import javax.servlet.DispatcherType;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;

public class LibSlf4jSCI implements ServletContainerInitializer
{
    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx)
    {
        ctx.addFilter("Slf4jFilter", Slf4jFilter.class)
            .addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST),
                false, "/*");
    }
}
