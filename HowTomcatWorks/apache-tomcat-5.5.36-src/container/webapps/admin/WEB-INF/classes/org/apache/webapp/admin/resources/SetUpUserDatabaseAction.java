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

package org.apache.webapp.admin.resources;

import java.io.IOException;
import java.util.Locale;
import javax.management.MBeanServer;
import javax.management.ObjectName;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.util.MessageResources;
import org.apache.webapp.admin.ApplicationServlet;


/**
 * <p>Implementation of <strong>Action</strong> that sets up and stashes
 * a <code>UserDatabaseForm</code> bean in request scope.  The form bean will have
 * a null <code>objectName</code> property if this form represents a UserDatabase
 * being added, or a non-null value for an existing UserDatabase.</p>
 *
 * @author Manveen Kaur
 * @version $Id: SetUpUserDatabaseAction.java 939536 2010-04-30 01:21:08Z kkolinko $
 * @since 4.1
 */

public final class SetUpUserDatabaseAction extends Action {

    // ----------------------------------------------------- Instance Variables


    /**
     * The MBeanServer we will be interacting with.
     */
    private MBeanServer mserver = null;


    // --------------------------------------------------------- Public Methods


    /**
     * Process the specified HTTP request, and create the corresponding HTTP
     * response (or forward to another web component that will create it).
     * Return an <code>ActionForward</code> instance describing where and how
     * control should be forwarded, or <code>null</code> if the response has
     * already been completed.
     *
     * @param mapping The ActionMapping used to select this instance
     * @param actionForm The optional ActionForm bean for this request (if any)
     * @param request The HTTP request we are processing
     * @param response The HTTP response we are creating
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet exception occurs
     */
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response)
        throws IOException, ServletException {

        // Look up the components we will be using as needed
        if (mserver == null) {
            mserver = ((ApplicationServlet) getServlet()).getServer();
        }
        MessageResources resources = getResources(request);
        Locale locale = getLocale(request);

        // Set up the form bean based on the creating or editing state
        String objectName = request.getParameter("objectName");
        String domain = request.getParameter("domain");
        
        UserDatabaseForm userDatabaseForm = new UserDatabaseForm();
        userDatabaseForm.setFactory
                            (SaveUserDatabaseAction.USERDB_FACTORY);
        userDatabaseForm.setType
                            (ResourceUtils.USERDB_CLASS);  
        userDatabaseForm.setDomain(domain);               

        if (objectName == null) {
            userDatabaseForm.setNodeLabel
                (resources.getMessage(locale, "resources.actions.userdb.create"));
            userDatabaseForm.setObjectName(null);
        } else {
            userDatabaseForm.setNodeLabel
                (resources.getMessage(locale, "resources.actions.userdb.edit"));
            userDatabaseForm.setObjectName(objectName);
                           
            String attribute = null;
            try {
                ObjectName oname = new ObjectName(objectName);
                attribute = "name";
                userDatabaseForm.setName
                    ((String) mserver.getAttribute(oname, attribute));
                attribute = "pathname";
                userDatabaseForm.setPath
                    ((String) mserver.getAttribute(oname, attribute));
                attribute = "description";
                userDatabaseForm.setDescription
                    ((String) mserver.getAttribute(oname, attribute));
            } catch (Exception e) {
                getServlet().log
                    (resources.getMessage(locale,
                        "users.error.attribute.get", attribute), e);
                response.sendError
                    (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                     resources.getMessage
                         (locale, "users.error.attribute.get", attribute));
                return (null);
            } 
        }
            
        // Stash the form bean and forward to the display page
        saveToken(request);
        request.setAttribute("userDatabaseForm", userDatabaseForm);
        return (mapping.findForward("UserDatabase"));

    }
}
