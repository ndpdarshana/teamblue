var targetLanguage="";
var usrToken;
var navToken;
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    $('[data-toggle="tooltip"]').tooltip();
    var pageUrl= window.location.href;
    navToken = pageUrl.split('=')[1];
    usrToken= "Token "+navToken;


});

function BrowseDoc()
{
    $('#errorMsg2').css("display","none");
    $('#errorMsg').css("display","none");
    $('#uploadSuccess').css("display","none");
    $("input[id='my_file']").click();

}


function RefreshTxt()
{
    $('#txtContent').val("");
}
function SetLanguage() {

    //$("#targetLanguage").html(language);
   // targetLanguage=language;
    $("#errorMsg").css("display","none");
    //var lang=$( "#languageSelect option:selected" ).text();
    targetLanguage=$( "#languageSelect" ).val();
}

function CheckPlagiarism() {

    $('#errorMsg3').css("display","none");
    if(targetLanguage==""||targetLanguage=="0")
    {
        $('#errorMsg').css("display","block");
    }
    else
    {
        var txtVal =$('#txtContent').val().trim();
        var selectedFile = document.getElementById('my_file').files[0];
        var langArray=[targetLanguage];
        if(txtVal!="")
        {
            var url = "http://localhost:8080/rest/text";
            var headers =
                {
                    "Content-Type": "application/json",
                    "Authorization": usrToken
                };
            var userData={
                "text": txtVal,
                "lang":langArray
            };
            $.ajax({
                url: url,
                type: "POST",
                contentType: "application/json;odata=verbose",
                headers: headers,
                data: JSON.stringify(userData),
                success: function (data) {
                    if(data.status=="accepted")
                    {
                        window.location.href="dashboard.html?token="+navToken;
                    }
                    else
                    {
                        console.log("error");
                    }

                },
                error: function (error) {
                    console.log(JSON.stringify(error));
                }
            });
        }
        else if(selectedFile)
        {
            UploadToServer();
        }
        else
        {
            $('#errorMsg3').css("display","block");
        }

    }
}

//Upload Files to Server
function UploadToServer() {
    var restUrl =  "http://localhost:8080/rest/uploadFile";
    var form = $('#fileUploadForm')[0];
    var fileData = new FormData(form);
     fileData.append('file', jQuery('#my_file')[0].files[0] );
     fileData.append('language',targetLanguage);
     var payload = {'file':jQuery('#my_file')[0].files[0],'language':targetLanguage};
       $.ajax({
        url: restUrl,
        type: "POST",
        headers: {
           "Authorization": usrToken
        },
        data: fileData,
        processData: false, //prevent jQuery from automatically transforming the data into a query string
        contentType: false,
        success: function (data) {
            if(data.status=="accepted")
            {
                window.location.href="dashboard.html?token="+usrToken;
            }
            else
            {
                console.log("error");
                $('#errorMsg2').css("display","block");
            }
           },
        error: function (ex) {
            console.log("Error in Uploading Files");
            $('#errorMsg2').css("display","block");
        }
    });
    var inputFiles = $("input[id='my_file']");
    inputFiles.replaceWith(inputFiles.val('').clone(true));// for resetting the file upload
    $('#uploadedFiles').text("");
}
function DisplayDoc() {

    var selectedFile = document.getElementById('my_file').files[0];
    $('#uploadedFiles').text(selectedFile.name);
}