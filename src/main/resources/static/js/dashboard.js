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
        // var text = data.docId;
        text2 = value['document'].documentName;
        // create the link + display the text
        cell2.appendChild(document.createTextNode(text2));

        cell3 = tbl_row.insertCell(2);
        link1 = document.createElement('a');
        // link1 = "test";
        link1.href = 'https://www.uow.edu.au/index.html' ;
        link1.textContent = 'english';
        // create the link + display the text
        cell3.appendChild(link1);

        cell4 = tbl_row.insertCell(3);
        link2 = document.createElement('a');
        // link2 = "test";
        link2.href = 'https://www.uow.edu.au/index.html' ;
        link2.textContent = 'english';
        // create the link + display the text
        cell4.appendChild(link2);
    })
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

