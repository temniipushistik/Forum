<#import "parts/common.ftlh" as c>

<@c.page>
    <H5>Please, edit your post</H5>

    <form method="post" enctype="multipart/form-data">
        <div class="form-group">
            <div class="m-3">

                <input type="text" value="${topic}" class="form-control" name="topic"
                       placeholder="Put new title here"/>

            </div>

            <div class="m-3">

                <input type="text" value="${description}" class="form-control" name="textOfArticle"
                       placeholder="Put new description here"/>

            </div>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">finish</button>
        </div>
    </form>


    Previous information:
    <div class="card my-3" style="width: 60rem;">


        <div class="m-3">

                <div><span> ${topic}</span></div>
                <div><span> ${description}</span></div>

        </div>

    </div>
</@c.page>





