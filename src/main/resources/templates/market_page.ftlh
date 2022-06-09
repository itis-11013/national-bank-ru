<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <title>Market Products</title>
    <@macros.header/>
</head>
<script>
    let page = 1;
    let size = 20

    function fetchProducts() {
        $.ajax({
                type: "GET",
                url: `http://188.93.211.195/central/productlist?page=1&size=1000`,
            }
        )
    }

    function nextPage() {
        page += 1;
        // fetchProducts()
    }

    function previousPage() {
        page -= 1;
        // fetchProducts()
    }

    function selectProduct() {

    }

    function createContract() {
        const productId = document.getElementById("productId").value;
        const productCount = document.getElementById("productCount").value;

        const formData = {
            productid: productId,
            count: productCount
        }

        $.ajax({
            type: "POST",
            url: "/ru/payment",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
                window.location.href = "/ru/product/"
            }
        });
    }
</script>
<body class="bg-light">
<@macros.navbar 3/>
<div class="container-fluid d-flex flex-column">
    <h2 class="fw-bold my-3 text-dark"> Open Market Products</h2>
    <form name="form" id="form" class="mb-4" style="max-width: 400px;">
        <div class="form-group mb-1 d-flex flex-column">
            <label>
                <input class="form-control" id="productName" type="text" placeholder="Product Name">
            </label>
        </div>
        <div class="form-group d-flex flex-column">
            <label>
                <input class="form-control" id="productCount" type="number" placeholder="Count">
            </label>
        </div>
        <label>
            <input class="form-control" hidden id="productId" type="number">
        </label>
        <div class="d-flex justify-content-center d-flex flex-column">
            <input class="btn btn-success" type="button" value="Buy" onclick=createContract()>
        </div>
    </form>
    <table class="table table-striped table-bordered table-hover thead-dark table-info">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Code</th>
            <th scope="col">Count</th>
            <th scope="col">Price</th>
            <th scope="col">Product Id</th>
            <th scope="col">Seller Id</th>
            <th scope="col"></th>
        </tr>
        </thead>
        <tbody>
        <#--        <#list products as product>-->
        <#--            <tr>-->
        <#--                <th>${product?index+1}</th>-->
        <#--                <th>${product.name}</th>-->
        <#--                <th>${product.code}</th>-->
        <#--                <th>${product.unit}</th>-->
        <#--                <th>${product.price} RUB</th>-->
        <#--                <th>${product.count}</th>-->
        <#--            </tr>-->
        <#--        </#list>-->
        </tbody>
    </table>
    <div class="form-control align-items-center d-flex justify-content-between">
        <button class="btn btn-info" onclick="previousPage()">Previous</button>
        <h3 id="pageNumber">1</h3>
        <button class="btn btn-info" onclick="nextPage()">Next</button>
    </div>
</div>
<@macros.footer/>
</body>
</html>
