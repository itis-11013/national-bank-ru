<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Sign In</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
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
<div class="card text-white bg-primary mb-3 rounded-0 d-flex align-items-center justify-content-center">
    <h1 class="card-header">
        National Bank RU
    </h1>
</div>
<div class="d-flex justify-content-center align-items-center">
    <div class="bg-light m-4 p-4 border border-primary border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
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
                <input class="btn btn-primary" type="submit" value="SIGN IN">
            </div>
            Don't have an account<a href="/ru/auth/signUp"> Sign up</a>
        </form>
    </div>
</div>
<div class="fixed-bottom text-white bg-secondary rounded-0 d-flex justify-content-center">
    <footer><small>&copy; Copyright 2022, National Bank RU</small></footer>
</div>
</body>
</html>
