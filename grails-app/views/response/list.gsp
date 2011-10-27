
<%@ page import="asker.Response" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'response.label', default: 'Response')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'response.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="response" title="${message(code: 'response.response.label', default: 'Response')}" />
                        
                            <th><g:message code="response.question.label" default="Question" /></th>
                        
                            <th><g:message code="response.responseUser.label" default="Response User" /></th>
                        
                            <g:sortableColumn property="pontos" title="${message(code: 'response.pontos.label', default: 'Pontos')}" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${responseInstanceList}" status="i" var="responseInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${responseInstance.id}">${fieldValue(bean: responseInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: responseInstance, field: "response")}</td>
                        
                            <td>${fieldValue(bean: responseInstance, field: "question")}</td>
                        
                            <td>${fieldValue(bean: responseInstance, field: "responseUser")}</td>
                        
                            <td>${fieldValue(bean: responseInstance, field: "pontos")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${responseInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
