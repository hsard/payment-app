<h1><g:message code="account.list.label"/></h1>

<f:table collection="${accountsList}"/>

<div class="pagination">
    <g:paginate total="${accountsCount ?: 0}"/>
</div>