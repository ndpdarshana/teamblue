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

// Create the dashboard table in Json
function displayTable(data) {
    console.log(data);
    var tbl_head = document.createElement("thead");
    var tbl_body = document.createElement("tbody");

    tbl_head += "<tr class=\"table100-head\">";
    tbl_head += "<th class=\"column1\">Id</th>";
    tbl_head += "<th class=\"column2\">Document name</th>";
    tbl_head += "<th class=\"column3\"></th>";
    tbl_head += "<th class=\"column4\"></th>";
    tbl_head += "</tr>";
    console.log(tbl_head);

    var cell1, cell2, cell3, cell4;
    var text1, text2, link1, link2;
    $.each(data, function(index, value ) {
        var tbl_row = tbl_body.insertRow();

        cell1 = tbl_row.insertCell(0);
        text1 = value.docId;
        cell1.appendChild(document.createTextNode(text1));

        cell2 = tbl_row.insertCell(1);
        text2 = value.docName;
        cell2.appendChild(document.createTextNode(text2));

        cell3 = tbl_row.insertCell(2);
        link1 = document.createElement('a');
        if(value['plagiarismCheck'].length > 0 ) {
            link1.href = value['plagiarismCheck'][0].url ;
            link1.target = "_blank";
            link1.textContent = value['plagiarismCheck'][0].lang;
        }
        cell3.appendChild(link1);

        cell4 = tbl_row.insertCell(3);
        link2 = document.createElement('a');
        if(value['plagiarismCheck'].length > 0) {
            link2.href = value['plagiarismCheck'][1].url ;
            link2.target = "_blank";
            link2.textContent = value['plagiarismCheck'][1].lang;
        }
        cell4.appendChild(link2);
    });
    $("#dashboardTable").html(tbl_head);
    $("#dashboardTable").append(tbl_body);
};

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
            displayTable(data);
        },
        error: function (error) {
            console.log(JSON.stringify(error));
        }
    });
}

