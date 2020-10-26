<!-- Insert these scripts at the bottom of the HTML, but before you use any Firebase services -->
<script src="${cp}/js/firebase/firebaseui.js"></script>
<link type="text/css" rel="stylesheet" href="${cp}/css/firebase/firebaseui.css" />
<!-- Firebase App (the core Firebase SDK) is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-app.js"></script>

<!-- If you enabled Analytics in your project, add the Firebase SDK for Analytics -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-analytics.js"></script>

<!-- Add Firebase products that you want to use -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-firestore.js"></script>
<script>
  var error = '${error}';
  console.log(error);
  if(error!==null && error!==''){
      openWarningToastr(error,"Warning");
  }
  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyCvLP_TBYMLRoGTU7BUYcHMWctNfqgim_o",
    authDomain: "multiechannel-74878.firebaseapp.com",
    databaseURL: "https://multiechannel-74878.firebaseio.com",
    projectId: "multiechannel-74878",
    storageBucket: "multiechannel-74878.appspot.com",
    messagingSenderId: "813077566601",
    appId: "1:813077566601:web:d0cad1a88407ee47a7e258",
    measurementId: "G-0HH10649PR"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  console.log(firebase.app().name);  
  
  var uiConfig = {
    callbacks: {
        signInSuccess: function (currentUser, credential, redirectUrl) {
            currentUser.getIdToken(true).then(function(idToken) {
                console.log("Singed in");
                // Send ajax request to verify user
                sessionCookie(idToken);
            });
        }
    },
    //Start it here 
    credentialHelper: firebaseui.auth.CredentialHelper.NONE,
    //End it here 
    signInFlow: 'popup',
    signInSuccessUrl: '${ServerConfiguration.LOCAL_URL}/pin',
    signInOptions: [
        // Leave the lines as is for the providers you want to offer your users.
//        firebase.auth.GoogleAuthProvider.PROVIDER_ID,
//        firebase.auth.FacebookAuthProvider.PROVIDER_ID,
//        firebase.auth.TwitterAuthProvider.PROVIDER_ID,
        firebase.auth.EmailAuthProvider.PROVIDER_ID
    ],
    // Terms of service url.
    tosUrl: '<your-tos-url>'
};

var ui = new firebaseui.auth.AuthUI(firebase.auth());
ui.start('#firebaseui-auth-container', uiConfig);
</script>
<script>
 function checkAuth() {
    // stop the form from submitting the normal way and refreshing the page
    event.preventDefault();
    // process the form
    $.ajax({
        type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
        url         : "${ServerConfiguration.LOCAL_URL}/authenticateUser", // the url where we want to POST
        dataType    : 'json', // what type of data do we expect back from the server
        encode          : true
    })
        // using the done promise callback
        .done(function(data) {
            // log data to the console so we can see
            hideLoading();
            if(data === "passed"){
                redirect("/pin");
            }else if(data === "pin"){
                redirect("/pin");
            }else {
                openErrorToastr(data,"Error");
            }
        });
};

function sessionCookie(idToken) {
    console.log(idToken);
    // stop the form from submitting the normal way and refreshing the page
    event.preventDefault();
    // process the form
    $.ajax({
        type        : 'POST', // define the type of HTTP verb we want to use (POST for our form)
        url         : "${ServerConfiguration.LOCAL_URL}/sessionCookie", // the url where we want to POST
        data        : {idToken: idToken},
        dataType    : 'json', // what type of data do we expect back from the server
        encode          : true
    })
        // using the done promise callback
        .done(function(data) {
            console.log(data);
            // log data to the console so we can see
            hideLoading();
            if(data === "success"){
                redirect("${ServerConfiguration.LOCAL_URL}/pin");
            }else {
                openErrorToastr(data,"Error");
            }
        });
};

</script>