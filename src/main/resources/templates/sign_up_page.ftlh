<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <title>Sign Up</title>
    <@macros.header/>
</head>
<script>
    function showPassword() {
        const x = document.getElementById("password");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }

    function createOrganization() {
        const formData = {
            name: document.getElementById("name").value,
            address: document.getElementById("address").value,
            password: document.getElementById("password").value
        }
        console.log(formData)
        $.ajax({
            type: "POST",
            methodName: "POST",
            url: "/ru/auth/singUp",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
                window.location.href = "/ru/auth/signIn"
            },
            error: function (error) {
                console.log(error)
            },
        });

    }
</script>
<body class="bg-light">
<div class="d-flex justify-content-center align-items-center">
    <div class="bg-light m-4 p-4 border border-success border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
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
        <div class="d-flex justify-content-center d-flex flex-column mt-5 mb-1">
            <input class="btn btn-success" type="button" value="Sign Up" onclick=createOrganization()>
        </div>
        Already have an account<a href="/ru/auth/signIn">Sign in</a>
    </div>
</div>
<@macros.footer/>
</body>
</html>
