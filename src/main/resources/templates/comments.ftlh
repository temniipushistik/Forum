
<#import "parts/common.ftlh" as c>
<@c.page>
    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        comments:
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" action="/textOfTopic/${idOfTopic}/comments">
                <div class="form-group">

                    <input type="text" class="form-control" name="textOfComment" placeholder="Put your comment"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

    <#list comments as comment>
        <div class="card my-3" style="width: 60rem;">


            <div class="m-3">
                <span> ${comment.getTextOfComment()}</span>
            </div>
            <div class="card-footer text-muted">
                Commented by ${comment.authorOfComment.username}
            </div>
        </div>


    </#list>

</@c.page>
