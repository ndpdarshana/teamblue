function templatePage(namePage)
{
    var usrToken;
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    window.location.href=namePage+".html?token="+usrToken;
}

function DocPage()
{
    templatePage("dashboard");
}

function HomePage()
{
    templatePage("home");
}

function userProfile()
{
    templatePage("UserProfile");
}

function contactUs()
{
    templatePage("ContactUs");
}