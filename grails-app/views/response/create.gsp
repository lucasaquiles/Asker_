

<%@ page import="asker.Response" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'response.label', default: 'Response')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${responseInstance}">
            <div class="errors">
                <g:renderErrors bean="${responseInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="response"><g:message code="response.response.label" default="Response" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: responseInstance, field: 'response', 'errors')}">
                                    <g:textArea name="response" cols="40" rows="5" value="${responseInstance?.response}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="question"><g:message code="response.question.label" default="Question" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: responseInstance, field: 'question', 'errors')}">
                                    <g:select name="question.id" from="${asker.Question.list()}" optionKey="id" value="${responseInstance?.question?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="responseUser"><g:message code="response.responseUser.label" default="Response User" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: responseInstance, field: 'responseUser', 'errors')}">
                                    <g:select name="responseUser.id" from="${asker.User.list()}" optionKey="id" value="${responseInstance?.responseUser?.id}"  />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="pontos"><g:message code="response.pontos.label" default="Pontos" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: responseInstance, field: 'pontos', 'errors')}">
                                    <g:textField name="pontos" value="${fieldValue(bean: responseInstance, field: 'pontos')}" />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
