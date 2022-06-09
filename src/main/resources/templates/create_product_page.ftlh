<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Create Product</title>
    <@macros.header/>
</head>
<script>
    function createProduct() {
        // const code = document.getElementById("product_catalogs")
        const formData = {
            name: document.getElementById("name").value,
            code: document.getElementById("product_catalogs").value,
            unit: document.getElementById("units").value,
            count: document.getElementById("count").value,
            price: document.getElementById("price").value
        }
        console.log(formData)
        $.ajax({
            type: "POST",
            url: "/ru/product/",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
            },
            errors: function (error) {
                console.log(error)
            },
        });
    }

</script>
<body class="bg-light">
<@macros.navbar 0/>
<div class="d-flex justify-content-center align-items-center">
    <div class="bg-light m-4 p-4 border border-primary border-5 rounded shadow p-3 mb-5 bg-white rounded rounded-3"
         style="max-width:400px;">
        <div class="d-flex flex-column align-items-center justify-content-center">
            <h3 class="font-weight-bold mb-5">Create Product</h3>
        </div>
        <form name="form" id="form">
            <div class="form-group mb-4 d-flex flex-column">
                <label>Name</label>
                <label>
                    <input class="form-control" id="name" type="text" placeholder="Product Name">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Price</label>
                <label>
                    <input class="form-control" id="price" type="number" placeholder="Price">
                </label>
            </div>
            <div class="form-group mb-4 d-flex flex-column">
                <label>Count</label>
                <label>
                    <input class="form-control" id="count" type="number" placeholder="Count">
                </label>
            </div>
            <label> Product Catalog Type</label>
            <div class="form-group mb-4 d-flex flex-column">
                <select id="product_catalogs" class="form-select" aria-label="Default select example">
                    <#list product_catalogs as product_catalog>
                        <option value=${product_catalog.code}>${product_catalog.name}</option>
                    </#list>
                </select>
            </div>
            <label>Product Unit Type</label>
            <select id="units" class="form-select" aria-label="Default select example">
                <#list units as unit>
                    <option value=${unit.code}>${unit.name}</option>
                </#list>
            </select>

        </form>
        <span id="span" class="text-danger font-weight-bold"></span>
        <div class="d-flex justify-content-center d-flex flex-column mt-5 mb-1">
            <input class="btn btn-primary" type="button" value="Add Product" onclick=createProduct()>
        </div>
    </div>
</div>
<@macros.footer/>
</body>
</html>
