<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js">
    function createOrganization() {
        const formData = JSON.stringify($("#form").serializeArray());
        $.ajax({
            type: "POST",
            url: "/ru/organization/",
            data: formData,
            success: function (response) {
                console.log(response)
            },
            errors: function (errors) {
                console.log(errors)
            },
            dataType: "json",
            contentType: "application/json"
        });
        window.stop();
    }
</script>

<body class="bg-light">
<div class="card text-white bg-primary mb-3 rounded-0">
    <h1 class="card-header">
        National Bank RU
    </h1>
</div>
<div class="bg-light m-4 p-4 border border-primary rounded" style="max-width: 400px;">
    <h2>CREATE ORGANIZATION</h2>
    <form name="form" id="form">
        <div class="form-group mb-4">
            <label>Name</label>
            <label>
                <input class="form-control" name="name" type="text" placeholder="ORGANIZATION">
            </label>
        </div>
        <div class="form-group mb-4">
            <label>Address</label>
            <label>
                <input class="form-control" name="address" type="text" placeholder="ADDRESS">
            </label>
        </div>
    </form>
    <div class="d-flex justify-content-center">
        <input class="btn btn-primary" type="button" value="Create" onclick=createOrganization()>
    </div>
</div>
<div class="fixed-bottom text-white bg-secondary rounded-0 d-flex justify-content-center">
    <footer><small>&copy; Copyright 2022, National Bank RU</small></footer>
</div>
</body>

</html>
