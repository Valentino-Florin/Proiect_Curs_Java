

function showPassword() {
  var x = document.getElementById("showpass");
  if (x.type === "password") {
    x.type = "text";
  } else {
    x.type = "password"
  }
}

function editProfile(){
  document.getElementById("username").disabled = false;
  document.getElementById("showpass").disabled = false;
  document.getElementById("showpassButton").disabled = false;
  document.getElementById("confirm_password").disabled = false;
}

function save(){
  document.getElementById("username").disabled = true;
  document.getElementById("showpass").disabled = true;
  document.getElementById("showpassButton").disabled = true;
  document.getElementById("confirm_password").disabled = true;
}

function activeTab(){
  document.getElementById()
}

function openTab(evt, info) {
  // Declare all variables
  var i, tabcontent, tablinks;

  // Get all elements with class="tabcontent" and hide them
  tabcontent = document.getElementsByClassName("tabcontent");
  for (i = 0; i < tabcontent.length; i++) {
    tabcontent[i].style.display = "none";
  }

  // Get all elements with class="tablinks" and remove the class "active"
  tablinks = document.getElementsByClassName("tablinks");
  for (i = 0; i < tablinks.length; i++) {
    tablinks[i].className = tablinks[i].className.replace(" active", "");
  }

  // Show the current tab, and add an "active" class to the link that opened the tab
  document.getElementById(info).style.display = "block";
  evt.currentTarget.class += " active";
}
