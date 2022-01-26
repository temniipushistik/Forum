
<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${topic}</h5>
    <#if !description??&&((authorId==currentUserId)||(user.isAdmin()))>
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

                    <#if (authorId==currentUserId)||user.isAdmin()>

                        <a href="/textOfTopic/${idOfTopic}/edit" class="btn btn-primary"> Edit </a>
                        <form method="post" action="/textOfTopic/${idOfTopic}/remove">
                            <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                            <button class="btn btn-danger" type="submit">Delete</button>

                        </form>
                    </#if>
                </div>

            </div>
            <div class="card-footer text-muted">
                Post has been published by ${author}
            </div>



        </div>

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
                    Comment has been published by ${comment.authorOfComment.username}
                </div>
            </div>


        </#list>



        </div>

        </div>
    </#if>


</@c.page>





