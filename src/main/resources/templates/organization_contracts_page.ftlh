<!doctype html>
<#import "components/macros.ftlh" as macros />
<html lang="en">

<head>
    <title>My Product</title>
    <@macros.header/>
</head>
<script>
    function payContract(innerId) {
        const formData = {
            contractid: innerId,
        }
        console.log(formData)
        $.ajax({
            type: "POST",
            url: "/ru/payment",
            data: JSON.stringify(formData),
            dataType: "json",
            contentType: "application/json",
            success: function (response) {
                alert(response.description)
            }
        });
    }
</script>
<body class="bg-light">
<@macros.navbar 4/>
<div class="container-fluid d-flex flex-column">
    <h2 class="fw-bold my-3 text-dark"> My Contract</h2>
    <table class="table table-striped table-bordered table-hover table-info">
        <thead class="thead-dark">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Product Name</th>
            <th scope="col">Count</th>
            <th scope="col">Amount</th>
            <th scope="col">Paid</th>
            <th scope="col">Payment Date</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <#list contracts as contract>
            <tr>
                <th>${contract?index+1}</th>
                <th>${contract.product.name}</th>
                <th>${contract.count}</th>
                <th>${contract.amount}</th>
                <th>
                    <#if contract.isPaid == true>
                        <p class="text-success fw-bold fs-2">YES</p>
                    </#if>
                    <#if contract.isPaid == false>
                        <p class="text-danger fw-bold fs-2">NO</p>
                    </#if>
                </th>
                <th>${contract.paymentDate}</th>
                <th>
                    <button <#if contract.isPaid == true>disabled</#if>
                            class="btn btn-success"
                            onclick="payContract(${contract.contractId})"
                            value="Pay"></button>
                </th>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
<@macros.footer/>
</body>
</html>
