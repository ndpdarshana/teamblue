$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    GetUserDetails();
});
/*function GetUserDetails()
{
    var url = ;
    var headers =
        {
            "accept": "application/json;odata=verbose",
            "X-RequestDigest": $("#__REQUESTDIGEST").val(),
            "content-Type": "application/json;odata=verbose"
        };
    var userData={
        "username":
    };
    $.ajax({
        url: url,
        type: "GET",
        contentType: "application/json;odata=verbose",
        headers: headers,
        data: JSON.stringify(userData),
        success: function (data) {



        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}*/
function ShowEditPane() {
    $('#userProfileDetails').css("display","none");
    $('#btnEdit').css("display","none");
    $('#userProfileDetailsEdit').css("display","block");
    $('#btnUpdateProfile').css("display","inline");

}
function UpdateProfile() {
    //Need to add code for validations
    /*var url = ;
    var headers =
        {
            "accept": "application/json;odata=verbose",
            "X-RequestDigest": $("#__REQUESTDIGEST").val(),
            "content-Type": "application/json;odata=verbose"
        };
    var userData={
        "username": ,
        "email": ,
        "firstName": ,
        "lastName": ,
        "dob": ,
    };
    $.ajax({
        url: url,
        type: "POST",
        contentType: "application/json;odata=verbose",
        headers: headers,
        data: JSON.stringify(userData),
        success: function (data) {

        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });*/
}