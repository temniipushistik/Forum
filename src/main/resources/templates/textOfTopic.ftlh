<#import "parts/security.ftlh" as s>
<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>
<@c.page>
    <h5>${topic}</h5>
    <#if !description??>
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">

                <label>
                    <input type="text" class="form-control" name="textOfArticle"
                           placeholder="Put your description here"/>
                </label>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Add description</button>
            </div>
        </form>
    </#if>

    <#if description??>
        <div class="card my-3" style="width: 60rem;">


            <div class="m-3">

                <div><span> ${description}</span></div>

                <div><span> ${authorId}</span></div>
                <div><span> ${currentUserId}</span></div>

                <div class="btn-group">
                    <#if authorId==currentUserId>

                    <a href="/textOfTopic/${idOfTopic}/edit" class="btn btn-primary"> Edit </a>
                    <form method="post" action="/textOfTopic/${idOfTopic}/remove">
                        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                        <button class="btn btn-danger" type="submit">Delete</button>

                    </form>

                </div>
                </#if>


            </div>

        </div>
    </#if>

</@c.page>





