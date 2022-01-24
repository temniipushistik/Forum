<#import "parts/common.ftlh" as c>

<@c.page>
    <h5>${topic}</h5>

    <form method="post" enctype="multipart/form-data">
        <div class="form-group">

            <label>
                <input type="text" class="form-control" name="textOfArticle" placeholder="Put your description here"/>
            </label>
        </div>
        <input type="hidden" name="_csrf" value="${_csrf.token}"/>
        <div class="form-group">
            <button type="submit" class="btn btn-primary">Add description</button>
        </div>
    </form>

    <div class="card my-3" style="width: 60rem;">


        <div class="m-3">
            <span> ${description}</span>

        </div>
    </div>
</@c.page>





