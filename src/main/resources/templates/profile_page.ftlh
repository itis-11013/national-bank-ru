<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Profile</title>
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

    function updateOrganization() {
        const formData = {
            name: document.getElementById("name").value,
            address: document.getElementById("address").value,
            password: document.getElementById("passwordCurrent").value,
            newPassword: document.getElementById("password").value,
        }
        console.log(formData)
        $.ajax({
            type: "PATCH",
            url: "/ru/organization/${currentUser.id}",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
                window.location.href = "/ru/auth/logout"
            }
        });
    }

</script>
<body class="bg-light">
<@macros.navbar 0/>
<div class="d-flex justify-content-center align-items-center flex-row">
    <div class="bg-light d-flex m-4 p-4 border border-primary border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3
    flex-column " style="width: 500px; height: 650px;">
        <div class="form-group mb-4 d-flex flex-column">
            <label>Name</label>
            <label>
                <input class="form-control" type="text" disabled value="${currentUser.name}">
            </label>
        </div>
        <div class="form-group mb-4 d-flex flex-column">
            <label>Address</label>
            <label>
                <input class="form-control" type="text" disabled value="${currentUser.address}">
            </label>
        </div>
        <div class="form-group mb-4 d-flex flex-column">
            <label>Balance</label>
            <label>
                <input class="form-control" type="text" disabled value="${currentUser.balance} RUB">
            </label>
        </div>
        <div class="form-group mb-4 d-flex flex-column">
            <label>Frozen Balance</label>
            <label>
                <input class="form-control" type="text" disabled value="${currentUser.frozenBalance} RUB">
            </label>
        </div>
        <div class="form-group mb-4 d-flex flex-column">
            <label>Created At</label>
            <label>
                <input class="form-control" type="text" disabled value="${currentUser.createdAt}">
            </label>
        </div>
        <div class="form-group mb-4 d-flex flex-column">
            <label>Update At</label>
            <label>
                <input class="form-control" type="text" disabled
                       value="${currentUser.updatedAt}">
            </label>
        </div>
    </div>
    <div class="bg-light m-4 p-4 border border-primary border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
         style="width: 400px; height: 650px;">
        <div class="d-flex flex-column align-items-center justify-content-center">
            <h3 class="font-weight-bold mb-5">Update Organization</h3>
        </div>
        <form name="form" id="form">
            <div class="form-group mb-4 d-flex flex-column">
                <label>Name</label>
                <label>
                    <input class="form-control" id="name" type="text" placeholder="Organization Name">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Address</label>
                <label>
                    <input class="form-control" id="address" type="text" placeholder="Address">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Current Password</label>
                <label>
                    <input class="form-control" id="passwordCurrent" type="password" placeholder="Current Password">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>New Password</label>
                <label>
                    <input class="form-control" id="password" type="password" placeholder="New Password">
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" id="ShowCurrentPass" type="checkbox" onclick="showPassword()">
                    Show Password
                </label>
            </div>
        </form>
        <div class="d-flex justify-content-center d-flex flex-column mt-5 mb-1">
            <input class="btn btn-primary" type="button" value="Update" onclick=updateOrganization()>
        </div>
    </div>
</div>
<@macros.footer/>
</body>
</html>
