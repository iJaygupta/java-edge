<!-- Insert these scripts at the bottom of the HTML, but before you use any Firebase services -->
<script src="https://cdn.firebase.com/libs/firebaseui/3.5.2/firebaseui.js"></script>
<link type="text/css" rel="stylesheet" href="https://cdn.firebase.com/libs/firebaseui/3.5.2/firebaseui.css" />
<!-- Firebase App (the core Firebase SDK) is always required and must be listed first -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-app.js"></script>

<!-- If you enabled Analytics in your project, add the Firebase SDK for Analytics -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-analytics.js"></script>

<!-- Add Firebase products that you want to use -->
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-auth.js"></script>
<script src="https://www.gstatic.com/firebasejs/7.7.0/firebase-firestore.js"></script>
<script>
  // Your web app's Firebase configuration
  var firebaseConfig = {
    apiKey: "AIzaSyAaAEny0js2aKJa7cGJ99ns3diewHT9dc4",
    authDomain: "adlis-ff673.firebaseapp.com",
    databaseURL: "https://adlis-ff673.firebaseio.com",
    projectId: "adlis-ff673",
    storageBucket: "adlis-ff673.appspot.com",
    messagingSenderId: "140965743287",
    appId: "1:140965743287:web:50a127bb0554d525a55b32"
  };
  // Initialize Firebase
  firebase.initializeApp(firebaseConfig);
  firebase.auth().onAuthStateChanged(function(user) {
  if (user) {
    // User is signed in.
    console.log(user);
    user.getIdToken(true).then(function(data) {
        console.log(data);
        document.cookie = encodeURIComponent("accessToken") + '=' + encodeURIComponent(data) + ';secure';
    });
  } else {
    // No user is signed in.
    document.cookie = encodeURIComponent("accessToken") + '=' + encodeURIComponent(null) + ';expires=Thu, 01 Jan 1970 00:00:01 GMT;';
    window.location.href = "/index";
  }
});
</script>