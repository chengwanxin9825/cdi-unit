/*
 * Copyright 2018, Red Hat, Inc. and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jglue.cdiunit.internal;

import java.net.URL;
import java.util.List;

import io.github.fastclasspathscanner.FastClasspathScanner;
import io.github.fastclasspathscanner.ScanResult;

/**
 * @author Sean Flanigan <a href="mailto:sflaniga@redhat.com">sflaniga@redhat.com</a>
 */
public class FCS4ClasspathScanner implements ClasspathScanner {

    @Override
    public List<URL> getClasspathURLs() {
        return new FastClasspathScanner().scan()
                .getClasspathURLs();
    }

    @Override
    public List<String> getClassNamesForClasspath(URL[] urls) {
        ScanResult scan = new FastClasspathScanner()
                .overrideClasspath(urls)
                .ignoreClassVisibility()
                .enableClassInfo()
                .scan();
        return scan.getAllClasses().getNames();
    }

    @Override
    public List<String> getClassNamesForPackage(String packageName, URL url) {
        ScanResult scan = new FastClasspathScanner()
                .whitelistPackagesNonRecursive(packageName)
                .overrideClasspath(url)
                .ignoreClassVisibility()
                .enableClassInfo()
                .scan();

        return scan.getAllClasses().getNames();
    }
}
