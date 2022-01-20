<#import "parts/common.ftlh" as c>

<@c.page>

    <div>
        <form method="post">
            <input type="text" name="text" placeholder="Input your topic" />
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of topics</div>
    <#list messages as message>
        <div>
            <b>${message.id}</b>
            <span>${message.text}</span>
             <strong>${message.authorName}</strong>
        </div>
    <#else>
        No message
    </#list>
</@c.page>