
(function ($) {
    "use strict";

    
    /*==================================================================
    [ Validate ]*/
    var input = $('.validate-input .input100');

    $('.validate-form').on('submit',function(){
        var check = true;

        for(var i=0; i<input.length; i++) {
            if(validate(input[i]) == false){
                showValidate(input[i]);
                check=false;
            }
        }

        if(check)
        {
            SubmitDetails();
        }
        return check;
    });


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
        else if($(input).attr('name') == 'pass2')
        {
            if($(input).val().trim()!=$('#userPassword').val())
            {
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
    
    function SubmitDetails() {
        alert("In Submit");
        var firstName=$('#txtFirstName').val().trim();;
        var lastName=$('#txtLastName').val().trim();
        var dob=$('#txtBday').val().trim();
        var email=$('#txtEmail').val().trim();
        var password=$('#userPassword').val().trim();
        var url = "http://localhost:8080/user/signup";
        var headers =
            {
                "accept": "application/json;odata=verbose",
                "X-RequestDigest": $("#__REQUESTDIGEST").val(),
                "content-Type": "application/json;odata=verbose"
            };
        var userData={
            "username":email ,
            "email":email ,
            "password":password ,
            "firstName":firstName ,
            "lastName":lastName ,
            "dob":dob
        };
        $.ajax({
            url: url,
            type: "POST",
            contentType: "application/json;odata=verbose",
            headers: headers,
            data: JSON.stringify(userData),
            success: function (data) {
                window.location.href = "index.html";
            },
            error: function (error) {
                console.log(JSON.stringify(error));
            }
        });

    }

})(jQuery);