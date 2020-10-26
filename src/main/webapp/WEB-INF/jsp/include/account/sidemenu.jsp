<!-- Left Sidebar Menu -->
<style>
    .mr-20{
        width: 14px;
    }
</style>
<div class="fixed-sidebar-left">
        <ul class="nav navbar-nav side-nav nicescroll-bar">
                <li class="navigation-header">
                        <span>Main</span> 
                        <i class="zmdi zmdi-more"></i>
                </li>
                <li>
                    <a href="javascript:void(0);" data-toggle="collapse" id="dashboard_dr" data-target="#home_dr"><div class="pull-left"><i class="zmdi zmdi-home mr-20"></i><span class="right-nav-text">Home</span></div><div class="pull-right"><span class="label label-success">4</span></div><div class="clearfix"></div></a>
                    <ul id="home_dr" class="collapse collapse-level-1">
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/admin"><i class="fa fa-tachometer mr-20"></i><span class="hide-menu">Dashboard</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/admin/facility/facility-list"><i class="fa fa-hospital-o mr-20"></i><span class="hide-menu">Facility</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/admin/user/user-list"><i class="fa fa-users mr-20"></i><span class="hide-menu">User</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/admin/bill/bill-format"><i class="fa fa-file-pdf-o mr-20"></i><span class="hide-menu">Bill Format</span></a>
                        </li>
                    </ul>
                </li>
<!--                <li>
                    <a href="javascript:void(0);" data-toggle="collapse" id="organization_dr" data-target="#pro_dr"><div class="pull-left"><i class="zmdi zmdi-shopping-basket mr-20"></i><span class="right-nav-text">Products</span></div><div class="pull-right"><span class="label label-success">6</span></div><div class="clearfix"></div></a>
                    <ul id="pro_dr" class="collapse collapse-level-1">
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/product-record-add" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Add Product</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/product-record-list" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Purchase Records</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/product-stock-list" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Facility Stock Records</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/sell-product" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Sell Product</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/sell-record-list" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Sell Records</span></a>
                        </li>
                        <li> <a href="${ServerConfiguration.LOCAL_URL}/product/invoice-record-list" aria-expanded="false"><i class="fa fa-book"></i><span class="hide-menu"> Invoice Records</span></a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);" data-toggle="collapse" id="admin_dr" data-target="#fun_dr"><div class="pull-left"><i class="zmdi zmdi-functions mr-20"></i><span class="right-nav-text">Functions</span></div><div class="pull-right"><span class="label label-success">5</span></div><div class="clearfix"></div></a>
                    <ul id="fun_dr" class="collapse collapse-level-1">
                        <li> <a href="/admin/test" aria-expanded="false"><i class="fa fa-area-chart"></i><span class="hide-menu"> Size Chart</span></a>
                        </li>
                        <li> <a href="/admin/user" aria-expanded="false"><i class="fa fa-adn"></i><span class="hide-menu"> Brand Group</span></a>
                        </li>
                        <li> <a href="/admin/patient" aria-expanded="false"><i class="fa fa-gears"></i><span class="hide-menu"> Manufacturer</span></a>
                        </li>
                        <li> <a href="/admin/report-format" aria-expanded="false"><i class="fa fa-users"></i><span class="hide-menu"> User Role</span></a>
                        </li>
                        <li> <a href="/admin/report-format" aria-expanded="false"><i class="fa fa-internet-explorer"></i><span class="hide-menu"> Ip White List</span></a>
                        </li>
                    </ul>
                </li>-->
        </ul>
</div>
<!-- /Left Sidebar Menu -->
<script>
    var url = window.location.pathname;
    console.log(url);
    if(url.includes("admin")){
        var element = document.getElementById("dashboard_dr");
        var el = document.getElementById("home_dr");
        element.classList.add("active");
        el.classList.remove("collapse");
        el.classList.add("active");
    }else if(url.includes("organization")){
        var element = document.getElementById("organization_dr");
        var el = document.getElementById("org_dr");
        element.classList.add("active");
        el.classList.remove("collapse");
        el.classList.add("active");
    }else if(url.includes("admin")){
        var element = document.getElementById("admin_dr");
        var eladmin = document.getElementById("ad_dr");
        element.classList.add("active");
        eladmin.classList.remove("collapse");
        eladmin.classList.add("active");
    }else if(url.includes("bill")){
        var element = document.getElementById("bill_dr");
        element.classList.add("active");
    }
</script>
