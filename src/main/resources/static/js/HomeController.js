var targetLanguage="";
var usrToken;
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    $('[data-toggle="tooltip"]').tooltip();
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    usrToken= "Token "+usrToken;


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
    targetLanguage="";
}
function SetLanguage(lang) {

    //$("#targetLanguage").html(language);
   // targetLanguage=language;
    $("#errorMsg").css("display","none");
    targetLanguage=lang.substring(0, 1);
    targetLanguage=targetLanguage.toLowerCase();
    $("#targetLanguage").html(lang);

}

function CheckPlagiarism() {

    $('#errorMsg3').css("display","none");
    if(targetLanguage=="")
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
                        window.location.href="dashboard.html?token="+usrToken;
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
            UploadDoc();
        }
        else
        {
            $('#errorMsg3').css("display","block");
        }

    }
}
function UploadDoc()
{

    var selectedFile = document.getElementById('my_file').files[0];
    var  fileName, reader, arrayBuffer;


                fileName = selectedFile.name;
                reader = new FileReader();
                reader.onload = function ()
                {
                    arrayBuffer = reader.result;
                    UploadToServer(fileName,arrayBuffer);

                };
                reader.readAsArrayBuffer(selectedFile);

    var inputFiles = $("input[id='my_file']");
    inputFiles.replaceWith(inputFiles.val('').clone(true));// for resetting the file upload
}


//Convert array buffer to binary
function _arrayBufferToBase64(buffer) {
    var binary = "";
    var bytes = new Uint8Array(buffer)
    var len = bytes.byteLength;
    for (var i = 0; i < len; i++) {
        binary += String.fromCharCode(bytes[i])
    }
    return binary;
}
//Upload Files to Server
function UploadToServer(fileName, arrayBuffer) {
    var restUrl =  "http://localhost:8080/rest/uploadFile";
    var binaryData = _arrayBufferToBase64(arrayBuffer);
    $.ajax({
        url: restUrl,
        type: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded",
            "Authorization": usrToken
        },
        binaryStringRequestBody: true,
        body: binaryData,
        success: function (data) {
            if(data.status=="accepted")
            {
                window.location.href="dashboard.html?token="+usrToken;
            }
            else
            {
                console.log("error");
            }
           },
        error: function (ex) {
            console.log("Error in Uploading Files")
        }
    });
}
function DisplayDoc() {

    var selectedFile = document.getElementById('my_file').files[0];
    $('#uploadedFiles').text(selectedFile.name);
}