// $(document).ready(function(){
//     $('#logbutton').click(function(){
//         var login = $('#login').val();
//         var password = $('#password').val();
//
//         if(login !== '' && password !== ''){
//             $.ajax({
//                 url:"/",
//                 method:"POST",
//                 data:{ login:login, password:password},
//                 success:function(res){
//                     if(res === "ok"){
//                         alert('successfully logged in')
//                     }
//                 }
//             })
//         }
//         else{
//             alert('All Fields are required!');
//         }
//     })
// });


// $(document).ready(function () {
//     $('#logbutton').click(function () {
//         var login = $("input#login").val();
//         var password = $("input#password").val();
//
//         alert(login + " " + password);
//
//         $.ajax({
//             type: "POST",
//             url: "/",
//             dataType: 'json',
//             async: false,
//             data: '{"login": "' + login + '", "password" : "' + password + '"}',
//             success: function () {
//                 alert('Authorization done!');
//             }
//         });
//     })
// });

function Authorize() {
    var login = $("input#login").val();
    var password = $("input#password").val();

    alert(login + " " + password);

    $.ajax({
        type: "POST",
        url: "/",
        dataType: 'json',
        async: false,
        data: '{"login": "' + login + '", "password" : "' + password + '"}',
        success: function () {
            alert('Authorization done!');
        }
    });
}