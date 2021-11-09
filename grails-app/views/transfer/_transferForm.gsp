<g:form resource="${this.transaction}" action="save" method="POST">
    <fieldset class="form">
        <f:all bean="transfer" order="sourceAccount,destinationAccount,amount" widget-optionValue="name"/>
    </fieldset>
    <fieldset class="buttons">
        <g:submitButton name="create" class="save"
                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
    </fieldset>
</g:form>