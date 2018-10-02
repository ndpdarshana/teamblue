var input;
var loginUrl = "http://localhost:8080/auth/token";
(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    input = $('.validate-input .input100');

})(jQuery);
    $('.validate-form .input100').each(function(){
        $(this).focus(function(){
           hideValidate(this);
        });
    });

    function validate (input) {
        if($(input).attr('type') == 'email' || $(input).attr('name') == 'email') {
            if($(input).val().trim().match(/^([a-zA-Z0-9_\-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([a-zA-Z0-9\-]+\.)+))([a-zA-Z]{1,5}|[0-9]{1,3})(\]?)$/) == null) {
                return false;
            }
        }
        else {
            if($(input).val().trim() == ''){
                return false;
            }
        }
    }

    function showValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).addClass('alert-validate');
    }

    function hideValidate(input) {
        var thisAlert = $(input).parent();

        $(thisAlert).removeClass('alert-validate');
    }




function OnSubmit()
{
    var check = true;

    for(var i=0; i<input.length; i++) {
        if(validate(input[i]) == false){
            showValidate(input[i]);
            check=false;
        }
    }

    if(check)
    {
        Login();
        //window.location.href="html/Home.html";
    }
    else
    {
        return check;
    }
}
function Login() {


    var userName =$('#txtEmail').val().trim();
    var password=$('#usrPassword').val().trim();
    var headers =
        {
            "Content-Type": "application/json"
        };
    var userData={
        "username":userName ,
        "email":userName ,
        "password":password
    };
    $.ajax({
        url: loginUrl,
        type: "POST",
        contentType: "application/json;odata=verbose",
        headers: headers,
        data: JSON.stringify(userData),
        success: function (data) {
            if(data.status=="UNAUTHORIZED")
            {
                $("#invalidLogin").css("display","block");
            }
            else
            {
                var token=data.token;
                window.location.href="html/Home.html?token="+token;
            }
        },
        error: function (error) {
            console.log(JSON.stringify(error));
            $("#invalidLogin").css("display","block");
        }
    });
}
