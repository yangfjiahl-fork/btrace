/*
 * Copyright (c) 2014, 2015, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */

package traces;

import com.sun.btrace.BTraceUtils;
import com.sun.btrace.annotations.BTrace;
import com.sun.btrace.annotations.OnMethod;
import com.sun.btrace.annotations.TLS;
import java.util.Deque;

/**
 * Sanity test to make sure the @TLS annotations work as expected.
 *
 * @author Jaroslav Bachorik
 */
@BTrace
public class TLSTest {
    @TLS
    public static Deque<Long> entryTimes = BTraceUtils.Collections.newDeque();

    @TLS
    public static String name;

    @TLS
    public static int x = 10;

    @TLS
    public static double y;

    @TLS
    public static final long z = 10L;

    @OnMethod(clazz = "resources.OnMethodTest", method="args")
    public static void testArgs(String a, long b, String[] c, int[] d) {
        BTraceUtils.push(entryTimes, b);
        name = a;
    }
}
