<!-- Standard Struts Entries -->
<%@ page language="java" import="java.net.URLEncoder" contentType="text/html;charset=utf-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/controls.tld" prefix="controls" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:html locale="true">

<%@ include file="../users/header.jsp" %>

<!-- Body -->
<body bgcolor="white" background="../images/PaperTexture.gif">

<!--Form -->

<html:errors/>

<html:form method="POST" action="/SaveRequestDumperValve">

  <bean:define id="thisObjectName" type="java.lang.String"
               name="requestDumperValveForm" property="objectName"/>
  <bean:define id="thisParentName" type="java.lang.String"
               name="requestDumperValveForm" property="parentObjectName"/>
  <html:hidden property="adminAction"/>
  <html:hidden property="parentObjectName"/>
  <html:hidden property="objectName"/>
  <html:hidden property="valveType"/>

  <table width="100%" border="0" cellspacing="0" cellpadding="0">
    <tr bgcolor="7171A5">
      <td width="81%">
       <div class="page-title-text" align="left">
         <logic:equal name="requestDumperValveForm" property="adminAction" value="Create">
            <bean:message key="actions.valves.create"/>
          </logic:equal>
          <logic:equal name="requestDumperValveForm" property="adminAction" value="Edit">
            <bean:write name="requestDumperValveForm" property="nodeLabel"/>
          </logic:equal>
       </div>
      </td>
      <td width="19%">
        <div align="right">
      <controls:actions>
            <controls:action selected="true"> ----<bean:message key="actions.available.actions"/>---- </controls:action>
            <controls:action> --------------------------------- </controls:action>
            <logic:notEqual name="requestDumperValveForm" property="adminAction" value="Create">
             <controls:action url='<%= "/DeleteValve.do?"  +
                                 "select=" + URLEncoder.encode(thisObjectName) +
                                 "&parent="+ URLEncoder.encode(thisParentName) %>'>
                <bean:message key="actions.valves.delete"/>
              </controls:action>
              </logic:notEqual>
       </controls:actions>
         </div>
      </td>
    </tr>
  </table>

    <%@ include file="../buttons.jsp" %>
  <br>

 <%-- Request Dumper Valve Properties --%>
 <table border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr> <td> <div class="table-title-text">
        <bean:message key="valve.request.properties"/>
    </div> </td> </tr>
  </table>

  <table class="back-table" border="0" cellspacing="0" cellpadding="0" width="100%">
    <tr>
      <td>
       <controls:table tableStyle="front-table" lineStyle="line-row">
            <controls:row header="true"
                labelStyle="table-header-text" dataStyle="table-header-text">
            <controls:label><bean:message key="service.property"/></controls:label>
            <controls:data><bean:message key="service.value"/></controls:data>
        </controls:row>

      <controls:row labelStyle="table-label-text" dataStyle="table-normal-text">
            <controls:label><bean:message key="connector.type"/>:</controls:label>
            <controls:data>
                 <logic:equal name="requestDumperValveForm" property="adminAction" value="Create">
                    <html:select property="valveType" onchange="IA_jumpMenu('self',this)">
                     <bean:define id="valveTypeVals" name="requestDumperValveForm" property="valveTypeVals"/>
                     <html:options collection="valveTypeVals" property="value" labelProperty="label"/>
                    </html:select>
                </logic:equal>
                <logic:equal name="requestDumperValveForm" property="adminAction" value="Edit">
                  <bean:write name="requestDumperValveForm" property="valveType" scope="session"/>
                </logic:equal>
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
