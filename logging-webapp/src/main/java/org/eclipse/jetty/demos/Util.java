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

package org.eclipse.jetty.demos;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

public class Util
{
    public static String toDebugString(Object obj)
    {
        if (obj == null)
            return "<null>";
        return String.format("%s@%X - %s", obj.getClass().getName(), obj.hashCode(), obj);
    }

    public static URI getCodeSourceLocation(Class<?> clazz)
    {
        try
        {
            ProtectionDomain domain = AccessController.doPrivileged((PrivilegedAction<ProtectionDomain>)clazz::getProtectionDomain);
            if (domain != null)
            {
                CodeSource source = domain.getCodeSource();
                if (source != null)
                {
                    URL location = source.getLocation();

                    if (location != null)
                    {
                        return location.toURI();
                    }
                }
            }
        }
        catch (URISyntaxException ignored)
        {
        }
        return null;
    }

    public static URI getClassLoaderLocation(Class<?> clazz, ClassLoader loader)
    {
        if (loader == null)
        {
            return null;
        }

        try
        {
            String resourceName = toClassReference(clazz.getName());
            URL url = loader.getResource(resourceName);
            if (url != null)
            {
                URI uri = url.toURI();
                String uriStr = uri.toASCIIString();
                if (uriStr.startsWith("jar:file:"))
                {
                    uriStr = uriStr.substring(4);
                    int idx = uriStr.indexOf("!/");
                    if (idx > 0)
                    {
                        return URI.create(uriStr.substring(0, idx));
                    }
                }
                return uri;
            }
        }
        catch (URISyntaxException ignored)
        {
        }
        return null;
    }

    public static URI getSystemClassLoaderLocation(Class<?> clazz)
    {
        return getClassLoaderLocation(clazz, ClassLoader.getSystemClassLoader());
    }

    public static String toClassReference(String className)
    {
        return className.replace('.', '/').concat(".class");
    }
}
