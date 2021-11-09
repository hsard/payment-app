<table class="table">
    <thead>
    <tr>
        <th scope="col">From</th>
        <th scope="col">To</th>
        <th scope="col">Amount</th>
    </tr>
    </thead>
    <tbody>
    <g:each var="transfer" status="index" in="${accountTransfers}">
        <tr>
            <td>${transfer.sourceAccount.name}</td>
            <td>${transfer.destinationAccount.name}</td>
            <td>${transfer.amount}</td>
        </tr>
    </g:each>
    </tbody>
</table>

<div class="pagination">
    <g:paginate total="${accountTransfers.size() ?: 0}"/>
</div>