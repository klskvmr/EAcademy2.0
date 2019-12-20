function getRegistration() {
    var login = $("input#login").val();
    var fio = $("input#fio").val();
    var email = $("input#email").val();
    var role = document.getElementById("dropdownMenuButton").innerText;
    var password = $("input#password").val();

    alert(login + " " + password);

    $.ajax({
        type: "POST",
        url: "/registration_user",
        contentType:"application/json",
        async: false,
        data: "{\"login\": \"" + login + "\", \"password\": \"" + password + "\", \"email\": \"" +  email +" \", \"fio\": \"" + fio + "\", \"role\": \"" + role + "\"}",
        success: function (responseData) {
            document.location.href = "/";
        }
    });
}