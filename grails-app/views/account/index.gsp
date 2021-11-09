<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>

    <title><g:message code="account.list.label"/></title>
</head>

<body>
<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>

<div id="list-account" class="content scaffold-list" role="main">
    <g:render template="templates/accountsTable"/>
</div>

</body>
</html>