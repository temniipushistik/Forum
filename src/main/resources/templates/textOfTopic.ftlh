<#import "parts/common.ftlh" as c>

<@c.page>
  <h5>${topic}</h5>
  <div class="form-group">

    <input type="text" class="form-control" name="text" placeholder="Share your idea here"/>
  </div>
  <input type="hidden" name="_csrf" value="${_csrf.token}"/>
  <div class="form-group">
    <button type="submit" class="btn btn-primary">Add</button>
  </div>

  <div class="card my-3" style="width: 60rem;">

    <div class="m-3">
      <span> Text</span>


    </div>
    <div class="card-footer text-muted">
      Post has been published by author
    </div>
  </div>
</@c.page>