//Redirect to the home, User profile, contact us page depending on the link clicked
function templatePage(namePage)
{
    var usrToken;
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    window.location.href=namePage+".html?token="+usrToken;
}

//Click of dashboard link
function DocPage()
{
    templatePage("dashboard");
}

//Click of Home link
function HomePage()
{
    templatePage("home");
}

//Click of user profile link
function userProfile()
{
    templatePage("UserProfile");
}

//Click of contact us link
function contactUs()
{
    templatePage("ContactUs");
}