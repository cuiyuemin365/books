/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.tester;


import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Part 1 of the request locale tests.  Should receive a Locale that
 * corresponds to "en_CA" Accept-Language header that is sent first.
 *
 * @author Craig R. McClanahan
 * @version $Id: GetLocales01.java 939535 2010-04-30 01:11:10Z kkolinko $
 */

public class GetLocales01 extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.reset();
        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();

        Locale expected = new Locale("en", "CA");
        Locale received = request.getLocale();
        if (received == null)
            writer.println("GetLocales01 FAILED - No locale was received");
        else if (!expected.equals(received))
            writer.println("GetLocales01 FAILED - Expected='" +
                           expected.toString() + "' Received='" +
                           received.toString() + "'");
        else
            writer.println("GetLocales01 PASSED");

        while (true) {
            String message = StaticLogger.read();
            if (message == null)
                break;
            writer.println(message);
        }
        StaticLogger.reset();

    }


}
