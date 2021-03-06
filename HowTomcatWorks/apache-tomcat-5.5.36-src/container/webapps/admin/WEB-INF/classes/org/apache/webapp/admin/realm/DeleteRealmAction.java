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


package org.apache.webapp.admin.realm;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Locale;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import org.apache.struts.util.MessageResources;

import org.apache.webapp.admin.ApplicationServlet;
import org.apache.webapp.admin.TomcatTreeBuilder;
import org.apache.webapp.admin.valve.ValveUtil;

/**
 * The <code>Action</code> that sets up <em>Delete Realms</em> transactions.
 *
 * @author Manveen Kaur
 * @version $Id: DeleteRealmAction.java 939536 2010-04-30 01:21:08Z kkolinko $
 */

public class DeleteRealmAction extends Action {
    
    
    /**
     * The MBeanServer we will be interacting with.
     */
    private MBeanServer mBServer = null;
    
    
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
        
        
        // Acquire the resources that we need
        Locale locale = getLocale(request);
        MessageResources resources = getResources(request);
        
        // Acquire a reference to the MBeanServer containing our MBeans
        try {
            mBServer = ((ApplicationServlet) getServlet()).getServer();
        } catch (Throwable t) {
            throw new ServletException
            ("Cannot acquire MBeanServer reference", t);
        }
        
        String pattern = null;
        // Set up a form bean containing the currently selected
        // objects to be deleted
        RealmsForm realmsForm = new RealmsForm();
        String select = request.getParameter("select");
        if (select != null) {
            String realms[] = new String[1];
            realms[0] = select;
            realmsForm.setRealms(realms);
            pattern = select;
        }
        request.setAttribute("realmsForm", realmsForm);
        
        // Accumulate a list of all available realms
        ArrayList list = new ArrayList();
        String parent = request.getParameter("parent");
        
        if (parent != null) {
            try {
                pattern = ValveUtil.getObjectName(
                             parent,TomcatTreeBuilder.REALM_TYPE);
            } catch (Exception e) {
                getServlet().log
                (resources.getMessage(locale, "users.error.select"));
                response.sendError
                (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                resources.getMessage(locale, "users.error.select"));
                return (null);
            }
        }
        
        try {
            Iterator items =
            mBServer.queryNames(new ObjectName(pattern), null).iterator();
            while (items.hasNext()) {
                list.add(items.next().toString());
            }
        } catch (Exception e) {
            getServlet().log
            (resources.getMessage(locale, "users.error.select"));
            response.sendError
            (HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
            resources.getMessage(locale, "users.error.select"));
            return (null);
        }
        
        Collections.sort(list);
        request.setAttribute("realmsList", list);
        
        // Forward to the list display page
        return (mapping.findForward("Realms"));
        
    }

}
