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
import java.security.Principal;
import javax.servlet.*;
import javax.servlet.http.*;

/**
 * Ensure that the "tomcat" user has been successfully authenticated.  This
 * should be guaranteed by the fact that this URI is protected by a security
 * constraint in the deployment descriptor.
 *
 * @author Craig R. McClanahan
 * @version $Id: Authentication01.java 939535 2010-04-30 01:11:10Z kkolinko $
 */

public class Authentication01 extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
        throws IOException, ServletException {

        response.setContentType("text/plain");
        PrintWriter writer = response.getWriter();
        String remoteUser = request.getRemoteUser();
        Principal userPrincipal = request.getUserPrincipal();
        String errors = "";
        if (remoteUser == null)
            errors += "  getRemoteUser() returned NULL.";
        else if (!remoteUser.equals("tomcat"))
            errors += "  remoteUser=" + remoteUser + ".";
        if (userPrincipal == null)
            errors += "  getUserPrincpal() returned NULL.";
        else if (!userPrincipal.getName().equals("tomcat"))
            errors += "  userPrincipal=" + userPrincipal.getName() + ".";
        if ((remoteUser != null) &&
            (userPrincipal != null) &&
            !remoteUser.equals(userPrincipal.getName()))
            errors += "  remoteUser=" + remoteUser + " userPrincipal=" +
                userPrincipal.getName();
        if (errors.length() > 0)
            writer.println("Authentication01 FAILED:" + errors);
        else
            writer.println("Authentication01 PASSED");

    }

}
