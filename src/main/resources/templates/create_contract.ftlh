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

<body class="bg-light">
    <div class="card text-white bg-primary mb-3 rounded-0">
        <h1 class="card-header">
            National Bank RU
        </h1>
    </div>
    <div class="bg-light m-4 p-4 border border-primary rounded" style="max-width: 400px;">
        <h2>CREATE CONTRACT</h2>
        <form action="/ru/... " method="post">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
            <div class="form-group mb-4">
                <label>Seller</label>
                <input class="form-control" name="seller" type="text" placeholder="ORGANIZATION">
            </div>
            <div class="form-group mb-4">
                <label>Buyer</label>
                <input class="form-control" name="buyer" type="text" placeholder="ORGANIZATION">
            </div>
            <div class="form-group mb-4">
                <label>Number of Units</label>
                <input class="form-control" name="count" type="text" placeholder="NUMBER">
            </div>
            <div class="form-group mb-4">
                <label>Price Per Unit</label>
                <input class="form-control" name="unitPrice" type="text" placeholder="PRICE">
            </div>
            <div class="form-group mb-4">
                <input name="isPaid" type="checkbox">
                <label>Paid</label>
            </div>
            <div class="form-group mb-4">
                <label>Payment Date</label>
                <input class="form-control" name="paymentDate" type="date" placeholder="DATE">
            </div>
            <div class="d-flex justify-content-center">
                <input class="btn btn-primary" type="submit" value="Create">
            </div>
        </form>
    </div>
    <div class="fixed-bottom text-white bg-secondary rounded-0 d-flex justify-content-center">
        <footer> <small>&copy; Copyright 2022, National Bank RU</small> </footer>
    </div>
</body>

</html>