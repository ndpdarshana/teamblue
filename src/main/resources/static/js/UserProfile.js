var usrToken;
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    console.log(usrToken);
    // usrToken= "Token "+usrToken;
    GetUserDetails();
});

// Create the table details table from Json
function displayTableDetails(data) {
    var tbl_body = document.createElement("tbody");

    var cell11, cell12, cell21, cell22, cell31, cell32;
    var tbl_row1, tbl_row2, tbl_row3;
    var text, link;

    tbl_row1 = tbl_body.insertRow();
    tbl_row2 = tbl_body.insertRow();
    tbl_row3 = tbl_body.insertRow();

    // First row
    cell11 = tbl_row1.insertCell(0);
    text = "First Name :" ;
    cell11.appendChild(document.createTextNode(text));

    cell12 = tbl_row1.insertCell(1);
    text = data.firstName;
    cell12.appendChild(document.createTextNode(text));


    // Second row
    cell21 = tbl_row2.insertCell(0);
    text = "Last Name :" ;
    cell21.appendChild(document.createTextNode(text));

    cell22 = tbl_row2.insertCell(1);
    text = data.lastName;
    cell22.appendChild(document.createTextNode(text));


    // Third row
    cell31 = tbl_row3.insertCell(0);
    text = "Email :";
    cell31.appendChild(document.createTextNode(text));

    cell32 = tbl_row3.insertCell(1);
    link = document.createElement('a');
    link.href = "mailto:"+data.email ;
    link.textContent = data.email;
    cell32.appendChild(link);


    $("#userProfileDetails").append(tbl_body);
};


function GetUserDetails()
{
    var url = "http://localhost:8080/user/"+usrToken;
    var headers =
        {
            "content-Type": "application/json"
        };
    $.ajax({
        url: url,
        type: "GET",
        contentType: "application/json",
        headers: headers,
        success: function (data) {
            console.log(data);
            displayTableDetails(data);
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}


function ShowEditPane() {
    $('#userProfileDetails').css("display","none");
    $('#btnEdit').css("display","none");
    $('#userProfileDetailsEdit').css("display","block");
    $('#btnUpdateProfile').css("display","inline");

}
// function UpdateProfile() {
//     //Need to add code for validations
//     /*var url = ;
//     var headers =
//         {
//             "accept": "application/json;odata=verbose",
//             "X-RequestDigest": $("#__REQUESTDIGEST").val(),
//             "content-Type": "application/json;odata=verbose"
//         };
//     var userData={
//         "username": ,
//         "email": ,
//         "firstName": ,
//         "lastName": ,
//         "dob": ,
//     };
//     $.ajax({
//         url: url,
//         type: "POST",
//         contentType: "application/json;odata=verbose",
//         headers: headers,
//         data: JSON.stringify(userData),
//         success: function (data) {
//
//         },
//         error: function (error) {
//             console.log(JSON.stringify(error));
//         }
//     });*/
// }