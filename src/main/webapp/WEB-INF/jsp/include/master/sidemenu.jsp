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
                    <a href="${ServerConfiguration.LOCAL_URL}/master/dashboard" id="dashboard_dr"><div class="pull-left"><i class="zmdi zmdi-landscape mr-20"></i><span class="right-nav-text">Dashboard</span></div><div class="clearfix"></div></a>
                    
                </li>
                <li>
                    <a href="javascript:void(0);" data-toggle="collapse" id="organization_dr" data-target="#org_dr"><div class="pull-left"><i class="fa fa-building mr-20"></i><span class="right-nav-text">Organization</span></div><div class="pull-right"><span class="label label-success">2</span></div><div class="clearfix"></div></a>
                    <ul id="org_dr" class="collapse collapse-level-1">
                        <li>
                                <a  href="${ServerConfiguration.LOCAL_URL}/master/organization/organization-add">Add</a>
                        </li>
                        <li>
                                <a  href="${ServerConfiguration.LOCAL_URL}/master/organization/organization-list">View</a>
                        </li>
                    </ul>
                </li>
                <li>
                    <a href="javascript:void(0);" data-toggle="collapse" id="admin_dr" data-target="#ad_dr"><div class="pull-left"><i class="zmdi zmdi-account mr-20"></i><span class="right-nav-text">Admin</span></div><div class="pull-right"><span class="label label-success">2</span></div><div class="clearfix"></div></a>
                    <ul id="ad_dr" class="collapse collapse-level-1">
                        <li>
                                <a  href="${ServerConfiguration.LOCAL_URL}/master/admin/admin-add">Add</a>
                        </li>
                        <li>
                                <a  href="${ServerConfiguration.LOCAL_URL}/master/admin/admin-list">View</a>
                        </li>
                    </ul>
                </li>
        </ul>
</div>
<!-- /Left Sidebar Menu -->
<script>
    var url = window.location.pathname;
    console.log(url);
    if(url.includes("dashboard")){
        var element = document.getElementById("dashboard_dr");
        element.classList.add("active");
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
