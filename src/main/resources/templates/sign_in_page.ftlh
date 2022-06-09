<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">
<head>
    <title>Sign In</title>
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
</script>
<body class="bg-light">
<div class="d-flex justify-content-center align-items-center">
    <div class="bg-light m-4 p-4 border border-success border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
         style="width:400px;">
        <div class="d-flex flex-column align-items-center justify-content-center">
            <h3 class="font-weight-bold mb-5">SIGN IN</h3>
        </div>
        <form action="/ru/auth/signIn" method="post">
            <div class="form-group mb-4 d-flex flex-column">
                <label>Name</label>
                <label>
                    <input class="form-control" name="name" type="text" placeholder="Name">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Password</label>
                <label>
                    <input class="form-control" id="password" name="password" type="password" placeholder="Password">
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="checkbox" onclick="showPassword()">
                    Show Password
                </label>
            </div>
            <div class="form-check">
                <input class="form-check-input" type="checkbox" name="remember-me" id="flexCheckDefault">
                <label class="form-check-label" for="flexCheckDefault">
                    Remember me
                </label>
            </div>
            <div class="d-flex justify-content-center d-flex flex-column mt-5 mb-2">
                <input class="btn btn-success" type="submit" value="SIGN IN">
            </div>
            Don't have an account<a href="/ru/auth/signUp"> Sign up</a>
        </form>
    </div>
</div>
<@macros.footer/>
</body>
</html>
