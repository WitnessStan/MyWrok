<html>
<head>
    <meta charset="utf-8">
    <title>嚯嚯嚯，成了</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-success alert-dismissable">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成了！
                </h4> <strong>${msg}</strong>3s后自动跳转至上一页<a href="${url}" class="alert-link">立即返回</a>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    setTimeout('location.href="${url}"', 3000);
</script>
</html>