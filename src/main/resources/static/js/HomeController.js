var targetLanguage="";
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");
    $('[data-toggle="tooltip"]').tooltip();


});

function BrowseDoc()
{
    $("input[id='my_file']").click();
}

function UploadDoc()
{
    var inputFiles = $("input[id='my_file']");
        if(inputFiles.length>0)
        {
            //code to upload files into server
            alert(inputFiles.length);
        }
}
function RefreshTxt()
{
    $('#txtContent').val("");
}
function SetLanguage() {

    //$("#targetLanguage").html(language);
   // targetLanguage=language;
    targetLanguage="";
    $("#errorMsg").css("display","none");
    $('input:checkbox:checked').each(function () {
        if(targetLanguage=="" && $(this).val()!="")
            targetLanguage=$(this).val();
        else
        targetLanguage =targetLanguage+" , "+ $(this).val();
    });
    if(targetLanguage=="")
    {
        $("#targetLanguage").html("Select Target Language");
    }
    else
    {
        $("#targetLanguage").html(targetLanguage);
    }

}

function CheckPlagiarism() {

    if(targetLanguage=="")
    {
        //$("#targetLanguage").addClass("error");
        //alert("Please select a target language.");
        $("#errorMsg").css("display","block");
    }
    else
    {
        var url = ;
        var headers =
            {
                "accept": "application/json;odata=verbose",
                "X-RequestDigest": $("#__REQUESTDIGEST").val(),
                "content-Type": "application/json;odata=verbose"
            };
        var userData={
            "targetLanguage":targetLanguage ,
            "text": ,

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
        });
    }
}