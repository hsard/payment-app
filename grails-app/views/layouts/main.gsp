<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><g:link class="list" controller="account" action="index"><g:message
                code="account.button.list.label"/></g:link></li>
        <li><g:link class="create" controller="transfer" action="create"><g:message
                code="transfer.button.create.label"/></g:link></li>

        <g:if env="development">
            <li><g:link class="list" controller="greenmail"><g:message code="mails.button.list.label"/></g:link></li>
        </g:if>

    </ul>
</div>

<g:layoutBody/>
<asset:javascript src="application.js"/>
</body>
</html>
