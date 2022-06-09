<#import "/spring.ftl" as spring />

<#macro header>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-pprn3073KE6tl6bjs2QrFaJGz5/SUsLqktiwsUTF55Jfv3qYSDhgCecCxMW52nD2"
            crossorigin="anonymous"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
</#macro>

<#macro navbar activeIndex>
    <nav class="navbar navbar-expand-sm navbar-dark bg-dark">
        <div class="container-fluid align-items-center">
            <h2 class="navbar-brand pt-1">National Bank Of Russia</h2>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="collapsibleNavbar">
                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a href="<@spring.url '/'/>" class="nav-link <#if activeIndex==0>disabled</#if>">Profile</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/product'/>" class="nav-link <#if activeIndex==1>disabled</#if>">Create
                            Product</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/product/'/>" class="nav-link <#if activeIndex==2>disabled</#if>">Products</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/admin/contract'/>"
                           class="nav-link <#if activeIndex==3>disabled</#if>">Market Products</a>
                    </li>
                    <li class="nav-item">
                        <a href="<@spring.url '/contract/' />"
                           class="nav-link <#if activeIndex==4>disabled</#if>">Contracts</a>
                    </li>
                </ul>
            </div>
            <div class="navbar-nav">
                <div class="nav-item text-nowrap">
                    <a class="nav-link px-3" href="<@spring.url '/auth/logout'/>">Sign out</a>
                </div>
            </div>
        </div>
    </nav>
</#macro>

<#macro footer>
    <div class="fixed-bottom text-white bg-secondary rounded-0 d-flex justify-content-center">
        <footer><small>&copy; Copyright 2022, National Bank RU</small></footer>
    </div>
</#macro>
