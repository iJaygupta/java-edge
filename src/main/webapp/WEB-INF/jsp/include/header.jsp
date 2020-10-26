<!-- Top Menu Items -->
<nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="mobile-only-brand pull-left">
                <div class="nav-header pull-left">
                        <div class="logo-wrap">
                                <a href="index.html">
                                        <img class="brand-img" src="${cp}/admin/dist/img/logo.png" alt="brand"/>
                                        <span class="brand-text">doodle</span>
                                </a>
                        </div>
                </div>	
                <a id="toggle_nav_btn" class="toggle-left-nav-btn inline-block ml-20 pull-left" href="javascript:void(0);"><i class="zmdi zmdi-menu"></i></a>
                <a id="toggle_mobile_search" data-toggle="collapse" data-target="#search_form" class="mobile-only-view" href="javascript:void(0);"><i class="zmdi zmdi-search"></i></a>
                <a id="toggle_mobile_nav" class="mobile-only-view" href="javascript:void(0);"><i class="zmdi zmdi-more"></i></a>
                <form id="search_form" role="search" class="top-nav-search collapse pull-left">
                        <div class="input-group">
                                <input type="text" name="example-input1-group2" class="form-control" placeholder="Search">
                                <span class="input-group-btn">
                                <button type="button" class="btn  btn-default"  data-target="#search_form" data-toggle="collapse" aria-label="Close" aria-expanded="true"><i class="zmdi zmdi-search"></i></button>
                                </span>
                        </div>
                </form>
        </div>
        <div id="mobile_only_nav" class="mobile-only-nav pull-right">
                <ul class="nav navbar-right top-nav pull-right">
                        <li>
                                <a id="open_right_sidebar" href="#"><i class="zmdi zmdi-settings top-nav-icon"></i></a>
                        </li>
                        <li class="dropdown app-drp">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="zmdi zmdi-apps top-nav-icon"></i></a>
                                <ul class="dropdown-menu app-dropdown" data-dropdown-in="slideInRight" data-dropdown-out="flipOutX">
                                        <li>
                                                <div class="app-nicescroll-bar">
                                                        <ul class="app-icon-wrap pa-10">
                                                                <li>
                                                                        <a href="weather.html" class="connection-item">
                                                                        <i class="zmdi zmdi-cloud-outline txt-info"></i>
                                                                        <span class="block">weather</span>
                                                                        </a>
                                                                </li>
                                                                <li>
                                                                        <a href="inbox.html" class="connection-item">
                                                                        <i class="zmdi zmdi-email-open txt-success"></i>
                                                                        <span class="block">e-mail</span>
                                                                        </a>
                                                                </li>
                                                                <li>
                                                                        <a href="calendar.html" class="connection-item">
                                                                        <i class="zmdi zmdi-calendar-check txt-primary"></i>
                                                                        <span class="block">calendar</span>
                                                                        </a>
                                                                </li>
                                                                <li>
                                                                        <a href="vector-map.html" class="connection-item">
                                                                        <i class="zmdi zmdi-map txt-danger"></i>
                                                                        <span class="block">map</span>
                                                                        </a>
                                                                </li>
                                                                <li>
                                                                        <a href="chats.html" class="connection-item">
                                                                        <i class="zmdi zmdi-comment-outline txt-warning"></i>
                                                                        <span class="block">chat</span>
                                                                        </a>
                                                                </li>
                                                                <li>
                                                                        <a href="contact-card.html" class="connection-item">
                                                                        <i class="zmdi zmdi-assignment-account"></i>
                                                                        <span class="block">contact</span>
                                                                        </a>
                                                                </li>
                                                        </ul>
                                                </div>	
                                        </li>
                                        <li>
                                                <div class="app-box-bottom-wrap">
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <a class="block text-center read-all" href="javascript:void(0)"> more </a>
                                                </div>
                                        </li>
                                </ul>
                        </li>
                        <li class="dropdown full-width-drp">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="zmdi zmdi-more-vert top-nav-icon"></i></a>
                                <ul class="dropdown-menu mega-menu pa-0" data-dropdown-in="fadeIn" data-dropdown-out="fadeOut">
                                        <li class="product-nicescroll-bar row">
                                                <ul class="pa-20">
                                                        <li class="col-md-3 col-xs-6 col-menu-list">
                                                                <a href="javascript:void(0);"><div class="pull-left"><i class="zmdi zmdi-landscape mr-20"></i><span class="right-nav-text">Dashboard</span></div><div class="pull-right"><i class="zmdi zmdi-caret-down"></i></div><div class="clearfix"></div></a>
                                                                <hr class="light-grey-hr ma-0"/>
                                                                <ul>
                                                                        <li>
                                                                                <a href="index.html">Analytical</a>
                                                                        </li>
                                                                        <li>
                                                                                <a  href="index2.html">Demographic</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="index3.html">Project</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="profile.html">profile</a>
                                                                        </li>
                                                                </ul>
                                                                <a href="widgets.html"><div class="pull-left"><i class="zmdi zmdi-flag mr-20"></i><span class="right-nav-text">widgets</span></div><div class="pull-right"><span class="label label-warning">8</span></div><div class="clearfix"></div></a>
                                                                <hr class="light-grey-hr ma-0"/>
                                                                <a href="documentation.html"><div class="pull-left"><i class="zmdi zmdi-book mr-20"></i><span class="right-nav-text">documentation</span></div><div class="clearfix"></div></a>
                                                                <hr class="light-grey-hr ma-0"/>
                                                        </li>
                                                        <li class="col-md-3 col-xs-6 col-menu-list">
                                                                <a href="javascript:void(0);">
                                                                        <div class="pull-left">
                                                                                <i class="zmdi zmdi-shopping-basket mr-20"></i><span class="right-nav-text">E-Commerce</span>
                                                                        </div>	
                                                                        <div class="pull-right"><span class="label label-success">hot</span>
                                                                        </div>
                                                                        <div class="clearfix"></div>
                                                                </a>
                                                                <hr class="light-grey-hr ma-0"/>
                                                                <ul>
                                                                        <li>
                                                                                <a href="e-commerce.html">Dashboard</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="product.html">Products</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="product-detail.html">Product Detail</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="add-products.html">Add Product</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="product-orders.html">Orders</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="product-cart.html">Cart</a>
                                                                        </li>
                                                                        <li>
                                                                                <a href="product-checkout.html">Checkout</a>
                                                                        </li>
                                                                </ul>
                                                        </li>
                                                        <li class="col-md-6 col-xs-12 preview-carousel">
                                                                <a href="javascript:void(0);"><div class="pull-left"><span class="right-nav-text">latest products</span></div><div class="clearfix"></div></a>
                                                                <hr class="light-grey-hr ma-0"/>
                                                                <div class="product-carousel owl-carousel owl-theme text-center">
                                                                        <a href="#">
                                                                                <img src="${cp}/admin/${cp}/admin/dist/img/chair.jpg" alt="chair">
                                                                                <span>Circle chair</span>
                                                                        </a>
                                                                        <a href="#">
                                                                                <img src="${cp}/admin/dist/img/chair2.jpg" alt="chair">
                                                                                <span>square chair</span>
                                                                        </a>
                                                                        <a href="#">
                                                                                <img src="${cp}/admin/dist/img/chair3.jpg" alt="chair">
                                                                                <span>semi circle chair</span>
                                                                        </a>
                                                                        <a href="#">
                                                                                <img src="${cp}/admin/dist/img/chair4.jpg" alt="chair">
                                                                                <span>wooden chair</span>
                                                                        </a>
                                                                        <a href="#">
                                                                                <img src="${cp}/admin/dist/img/chair2.jpg" alt="chair">
                                                                                <span>square chair</span>
                                                                        </a>								
                                                                </div>
                                                        </li>
                                                </ul>
                                        </li>	
                                </ul>
                        </li>
                        <li class="dropdown alert-drp">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="zmdi zmdi-notifications top-nav-icon"></i><span class="top-nav-icon-badge">5</span></a>
                                <ul  class="dropdown-menu alert-dropdown" data-dropdown-in="bounceIn" data-dropdown-out="bounceOut">
                                        <li>
                                                <div class="notification-box-head-wrap">
                                                        <span class="notification-box-head pull-left inline-block">notifications</span>
                                                        <a class="txt-danger pull-right clear-notifications inline-block" href="javascript:void(0)"> clear all </a>
                                                        <div class="clearfix"></div>
                                                        <hr class="light-grey-hr ma-0"/>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="streamline message-nicescroll-bar">
                                                        <div class="sl-item">
                                                                <a href="javascript:void(0)">
                                                                        <div class="icon bg-green">
                                                                                <i class="zmdi zmdi-flag"></i>
                                                                        </div>
                                                                        <div class="sl-content">
                                                                                <span class="inline-block capitalize-font  pull-left truncate head-notifications">
                                                                                New subscription created</span>
                                                                                <span class="inline-block font-11  pull-right notifications-time">2pm</span>
                                                                                <div class="clearfix"></div>
                                                                                <p class="truncate">Your customer subscribed for the basic plan. The customer will pay $25 per month.</p>
                                                                        </div>
                                                                </a>	
                                                        </div>
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <div class="sl-item">
                                                                <a href="javascript:void(0)">
                                                                        <div class="icon bg-yellow">
                                                                                <i class="zmdi zmdi-trending-down"></i>
                                                                        </div>
                                                                        <div class="sl-content">
                                                                                <span class="inline-block capitalize-font  pull-left truncate head-notifications txt-warning">Server #2 not responding</span>
                                                                                <span class="inline-block font-11 pull-right notifications-time">1pm</span>
                                                                                <div class="clearfix"></div>
                                                                                <p class="truncate">Some technical error occurred needs to be resolved.</p>
                                                                        </div>
                                                                </a>	
                                                        </div>
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <div class="sl-item">
                                                                <a href="javascript:void(0)">
                                                                        <div class="icon bg-blue">
                                                                                <i class="zmdi zmdi-email"></i>
                                                                        </div>
                                                                        <div class="sl-content">
                                                                                <span class="inline-block capitalize-font  pull-left truncate head-notifications">2 new messages</span>
                                                                                <span class="inline-block font-11  pull-right notifications-time">4pm</span>
                                                                                <div class="clearfix"></div>
                                                                                <p class="truncate"> The last payment for your G Suite Basic subscription failed.</p>
                                                                        </div>
                                                                </a>	
                                                        </div>
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <div class="sl-item">
                                                                <a href="javascript:void(0)">
                                                                        <div class="sl-avatar">
                                                                                <img class="img-responsive" src="${cp}/admin/dist/img/avatar.jpg" alt="avatar"/>
                                                                        </div>
                                                                        <div class="sl-content">
                                                                                <span class="inline-block capitalize-font  pull-left truncate head-notifications">Sandy Doe</span>
                                                                                <span class="inline-block font-11  pull-right notifications-time">1pm</span>
                                                                                <div class="clearfix"></div>
                                                                                <p class="truncate">Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit</p>
                                                                        </div>
                                                                </a>	
                                                        </div>
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <div class="sl-item">
                                                                <a href="javascript:void(0)">
                                                                        <div class="icon bg-red">
                                                                                <i class="zmdi zmdi-storage"></i>
                                                                        </div>
                                                                        <div class="sl-content">
                                                                                <span class="inline-block capitalize-font  pull-left truncate head-notifications txt-danger">99% server space occupied.</span>
                                                                                <span class="inline-block font-11  pull-right notifications-time">1pm</span>
                                                                                <div class="clearfix"></div>
                                                                                <p class="truncate">consectetur, adipisci velit.</p>
                                                                        </div>
                                                                </a>	
                                                        </div>
                                                </div>
                                        </li>
                                        <li>
                                                <div class="notification-box-bottom-wrap">
                                                        <hr class="light-grey-hr ma-0"/>
                                                        <a class="block text-center read-all" href="javascript:void(0)"> read all </a>
                                                        <div class="clearfix"></div>
                                                </div>
                                        </li>
                                </ul>
                        </li>
                        <li class="dropdown auth-drp">
                                <a href="#" class="dropdown-toggle pr-0" data-toggle="dropdown"><img src="${cp}/admin/dist/img/user1.png" alt="user_auth" class="user-auth-img img-circle"/><span class="user-online-status"></span></a>
                                <ul class="dropdown-menu user-auth-dropdown" data-dropdown-in="flipInX" data-dropdown-out="flipOutX">
                                        <li>
                                                <a href="profile.html"><i class="zmdi zmdi-account"></i><span>Profile</span></a>
                                        </li>
                                        <li>
                                                <a href="#"><i class="zmdi zmdi-card"></i><span>my balance</span></a>
                                        </li>
                                        <li>
                                                <a href="inbox.html"><i class="zmdi zmdi-email"></i><span>Inbox</span></a>
                                        </li>
                                        <li>
                                                <a href="#"><i class="zmdi zmdi-settings"></i><span>Settings</span></a>
                                        </li>
                                        <li class="divider"></li>
                                        <li class="sub-menu show-on-hover">
                                                <a href="#" class="dropdown-toggle pr-0 level-2-drp"><i class="zmdi zmdi-check text-success"></i> available</a>
                                                <ul class="dropdown-menu open-left-side">
                                                        <li>
                                                                <a href="#"><i class="zmdi zmdi-check text-success"></i><span>available</span></a>
                                                        </li>
                                                        <li>
                                                                <a href="#"><i class="zmdi zmdi-circle-o text-warning"></i><span>busy</span></a>
                                                        </li>
                                                        <li>
                                                                <a href="#"><i class="zmdi zmdi-minus-circle-outline text-danger"></i><span>offline</span></a>
                                                        </li>
                                                </ul>	
                                        </li>
                                        <li class="divider"></li>
                                        <li>
                                                <a href="#"><i class="zmdi zmdi-power"></i><span>Log Out</span></a>
                                        </li>
                                </ul>
                        </li>
                </ul>
        </div>	
</nav>
<!-- /Top Menu Items -->