<%-- 
    Document   : TestJSPJSF
    Created on : Oct 27, 2012, 12:59:13 AM
    Author     : Mihindu Gajaba Karunarathne
    Company    : Alpha Development Team (Pvt) Ltd
/**
 *
 * @author Mihindu Gajaba Karunarathne
 * Alpha Development Team (Pvt) Ltd
 * 
 */
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<f:view>
    <html>
        <h:head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
            <ui:insert name="title">JSP Page</ui:insert>
        </h:head>
        <h:body>
            <h1><h:outputText value="Hello World!"/></h1>
            <jsp:include page="/UserInfoGrabber" />
        </h:body>
    </html>
</f:view>
