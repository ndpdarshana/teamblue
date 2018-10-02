var usrToken;
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    $('[data-toggle="tooltip"]').tooltip();
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    usrToken= "Token "+usrToken;
    GetAllDocs();
});

function GetAllDocs()
{
    var url = "http://localhost:8080/rest/documents/all";
    var headers =
        {
            "Content-Type": "application/json",
            "Authorization": usrToken
        };

    $.ajax({
        url: url,
        type: "GET",
        headers: headers,
        success: function (data) {
            console.log(data);
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}