<#import "parts/common.ftlh" as c>

<@c.page>

    <a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
       aria-controls="collapseExample">
        Add new Topic
    </a>
    <div class="collapse" id="collapseExample">
        <div class="form-group mt-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">

                    <input type="text" class="form-control" name="title" placeholder="Put your topic"/>
                </div>
                <input type="hidden" name="_csrf" value="${_csrf.token}"/>
                <div class="form-group">
                    <button type="submit" class="btn btn-primary">Add</button>
                </div>
            </form>
        </div>
    </div>

        <#list messages as message>
              <div class="card my-3" style="width: 60rem;">

                <div class="m-3">
                    <span> <a href="/textOfTopic/${message.id}">${message.text}</a></span>


                </div>
                <div class="card-footer text-muted">
                  Post has been published by ${message.authorName}
                </div>
            </div>
        <#else>
            No message
        </#list>
    </div>
</@c.page>