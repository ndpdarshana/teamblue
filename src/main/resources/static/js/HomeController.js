var targetLanguage="";
$(function(){
    $("#pageHeader").load("header.html");
    $("#pageFooter").load("footer.html");


});

function BrowseDoc()
{
    $("input[id='my_file']").click();
}

function SetLanguage(language) {
    $("#targetLanguage").removeClass("error");
    $("#targetLanguage").html(language);
    targetLanguage=language;
}

function CheckPlagiarism() {
    if(targetLanguage=="")
    {
        $("#targetLanguage").addClass("error");
        alert("Please select a target language.");
    }
    else
    {

    }
}