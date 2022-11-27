<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <title>My Product</title>
    <@macros.header/>
</head>

<body class="bg-light">
<@macros.navbar 2/>
<div class="container-fluid d-flex flex-column">
    <h2 class="fw-bold my-3 text-dark"> My Products</h2>
    <table class="table table-striped table-bordered table-hover thead-dark table-info">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Catalog</th>
            <th scope="col">Unit</th>
            <th scope="col">Price</th>
            <th scope="col">Count</th>
        </tr>
        </thead>
        <tbody>
        <#list products as product>
            <tr>
                <th>${product?index+1}</th>
                <th>${product.name}</th>
                <th>${product.code}</th>
                <th>${product.unit}</th>
                <th>${product.price} RUB</th>
                <th>${product.count}</th>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<@macros.footer/>
</body>
</html>
