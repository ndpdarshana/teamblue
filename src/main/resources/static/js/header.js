function DocPage()
{
    var usrToken;
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    window.location.href="dashboard.html?token="+usrToken;
}

function HomePage()
{
    var usrToken;
    var pageUrl= window.location.href;
    usrToken = pageUrl.split('=')[1];
    window.location.href="home.html?token="+usrToken;
}