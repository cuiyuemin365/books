<!--
  Licensed to the Apache Software Foundation (ASF) under one or more
  contributor license agreements.  See the NOTICE file distributed with
  this work for additional information regarding copyright ownership.
  The ASF licenses this file to You under the Apache License, Version 2.0
  (the "License"); you may not use this file except in compliance with
  the License.  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.
-->
<!-- Standard Struts Entries -->
<%@ page language="java" import="java.net.URLEncoder" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="/WEB-INF/controls.tld" prefix="controls" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html locale="true">

<%@ include file="../users/header.jsp" %>

<!-- Body -->
<body bgcolor="white" background="../images/PaperTexture.gif">

<!--Form -->

<html:errors/>

<html:form method="POST" action="/SaveJDBCRealm">

  <bean:define id="thisObjectName" type="java.lang.String"
               name="jdbcRealmForm" property="objectName"/>
  <html:hidden property="adminAction"/>
  <html:hidden property="parentObjectName"/>
  <html:hidden property="objectName"/>
  <html:hidden property="allowDeletion"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="7171A5">
      <td width="81%">
       <div class="page-title-text" align="left">
         <logic:equal name="jdbcRealmForm" property="adminAction" value="Create">
            <bean:message key="actions.realms.create"/>
          </logic:equal>
          <logic:equal name="jdbcRealmForm" property="adminAction" value="Edit">
            <bean:write name="jdbcRealmForm" property="nodeLabel"/>
          </logic:equal>
       </div>
      </td>
      <td align="right" nowrap>
        <div class="page-title-text">
      <controls:actions label="Realm Actions">
            <controls:action selected="true"> ----<bean:message key="actions.available.actions"/>---- </controls:action>
            <controls:action> --------------------------------- </controls:action>
            <logic:notEqual name="jdbcRealmForm" property="adminAction" value="Create">
            <logic:notEqual name="jdbcRealmForm" property="allowDeletion" value="false">
             <controls:action url='<%= "/DeleteRealm.do?select=" +
                                        URLEncoder.encode(thisObjectName,"UTF-8") %>'>
                <bean:message key="actions.realms.delete"/>
              </controls:action>
               </logic:notEqual>
             </logic:notEqual>
       </controls:actions>
         </div>
      </td>
    </tr>
  </table>
    <%@ include file="../buttons.jsp" %>
  <br>

  <table class="back-table" border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
      <td>
       <controls:table tableStyle="front-table" lineStyle="line-row">
            <controls:row header="true"
                labelStyle="table-header-text" dataStyle="table-header-text">
            <controls:label><bean:message key="service.property"/></controls:label>
            <controls:data><bean:message key="service.value"/></controls:data>
        </controls:row>

      <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="type">
            <controls:label><bean:message key="connector.type"/>:</controls:label>
            <controls:data>
                 <logic:equal name="jdbcRealmForm" property="adminAction" value="Create">
                    <html:select property="realmType" onchange="IA_jumpMenu('self',this)" styleId="type">
                     <bean:define id="realmTypeVals" name="jdbcRealmForm" property="realmTypeVals"/>
                     <html:options collection="realmTypeVals" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:equal name="jdbcRealmForm" property="adminAction" value="Edit">
                  <bean:write name="jdbcRealmForm" property="realmType" scope="session"/>
                </logic:equal>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="driver">
            <controls:label><bean:message key="realm.driver"/>:</controls:label>
            <controls:data>
              <html:text property="driver" size="30" styleId="driver"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="passwd">
            <controls:label><bean:message key="realm.passwd"/>:</controls:label>
            <controls:data>
                <html:text property="connectionPassword" size="30" styleId="passwd"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="url">
            <controls:label><bean:message key="realm.url"/>:</controls:label>
            <controls:data>
                <html:text property="connectionURL" size="30" styleId="url"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="username">
            <controls:label><bean:message key="realm.userName"/>:</controls:label>
            <controls:data>
                <html:text property="connectionName" size="30" styleId="username"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="digest">
            <controls:label><bean:message key="realm.digest"/>:</controls:label>
            <controls:data>
                <html:text property="digest" size="30" styleId="digest"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="passwordCol">
            <controls:label><bean:message key="realm.passwordCol"/>:</controls:label>
            <controls:data>
                <html:text property="passwordCol" size="30" styleId="passwordCol"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="roleNameCol">
            <controls:label><bean:message key="realm.roleNameCol"/>:</controls:label>
            <controls:data>
                <html:text property="roleNameCol" size="30" styleId="roleNameCol"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="userNameCol">
            <controls:label><bean:message key="realm.userNameCol"/>:</controls:label>
            <controls:data>
                <html:text property="userNameCol" size="30" styleId="userNameCol"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="userRoleTable">
            <controls:label><bean:message key="realm.userRoleTable"/>:</controls:label>
            <controls:data>
                <html:text property="roleTable" size="30" styleId="userRoleTable"/>
            </controls:data>
        </controls:row>

        <controls:row labelStyle="table-label-text" dataStyle="table-normal-text" styleId="userTable">
            <controls:label><bean:message key="realm.userTable"/>:</controls:label>
            <controls:data>
                <html:text property="userTable" size="30" styleId="userTable"/>
            </controls:data>
        </controls:row>

      </controls:table>
      </td>
    </tr>
  </table>
    <%@ include file="../buttons.jsp" %>
  <br>
  </html:form>
<p>&nbsp;</p>
</body>
</html:html>
