<!doctype html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign Up</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script>
    async function createOrganization() {
        const formData = {
            name: document.getElementById("name").value,
            address: document.getElementById("address").value,
            password: document.getElementById("password").value
        }
        console.log(formData)
        $.ajax({
            type: "POST",
            url: "/ru/organization/",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
            },
            errors: function (errors) {
                console.log(errors)
                $("#span").text(errors.text);
            },
        });

        function showPassword() {
            const x = document.getElementById("password");
            if (x.type === "password") {
                x.type = "text";
            } else {
                x.type = "password";
            }
        }
    }
</script>

<body class="bg-light">
<div class="card text-white bg-primary mb-3 rounded-0 d-flex align-items-center justify-content-center">
    <h1 class="card-header">
        National Bank RU
    </h1>
</div>
<div class="d-flex justify-content-center align-items-center">
    <div class="bg-light m-4 p-4 border border-primary border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
         style="max-width:400px;">
        <div class="d-flex flex-column align-items-center justify-content-center">
            <h3 class="font-weight-bold mb-5">CREATE ORGANIZATION</h3>
        </div>
        <form name="form" id="form">
            <div class="form-group mb-4 d-flex flex-column">
                <label>Name</label>
                <label>
                    <input class="form-control" id="name" type="text" placeholder="Name">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Address</label>
                <label>
                    <input class="form-control" id="address" type="text" placeholder="Address">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Password</label>
                <label>
                    <input class="form-control" id="password" type="password" placeholder="Password">
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" onclick="showPassword()">
                    Show Password
                </label>
            </div>
        </form>
        <span id="span" class="text-danger font-weight-bold"></span>
        <div class="d-flex justify-content-center d-flex flex-column mt-5 mb-1">
            <input class="btn btn-primary" type="button" value="Create" onclick=createOrganization()>
        </div>
        Already have an account<a href="/ru/auth/signIn"> Sign in</a>
    </div>
</div>
<div class="fixed-bottom text-white bg-secondary rounded-0 d-flex justify-content-center">
    <footer><small>&copy; Copyright 2022, National Bank RU</small></footer>
</div>
</body>
</html>
