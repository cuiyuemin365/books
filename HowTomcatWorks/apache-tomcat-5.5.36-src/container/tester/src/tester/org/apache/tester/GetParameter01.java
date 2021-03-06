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
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Test retrieval of parameters.  The client is expected to send a request
 * URI like "/tester/GetParameter01?foo=1".
 *
 * @author Craig R. McClanahan
 * @version $Id: GetParameter01.java 939535 2010-04-30 01:11:10Z kkolinko $
 */

public class GetParameter01 extends GenericServlet {

    public void service(ServletRequest request, ServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        String expected = "1";
        String result = request.getParameter("foo");
        if (expected.equals(result)) {
            writer.println("GetParameter01 PASSED");
        } else {
            writer.println("GetParameter01 FAILED - Received '" + result +
                           "' instead of '" + expected + "'");
        }
        while (true) {
            String message = StaticLogger.read();
            if (message == null)
                break;
            writer.println(message);
        }
        StaticLogger.reset();

    }

}
