function showPassword() {
  var x = document.getElementById("showpass");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password"
  }
}

function edit(){
  window.location.href = "/editprofile";
}



