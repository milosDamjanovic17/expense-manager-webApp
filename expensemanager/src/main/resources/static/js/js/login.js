$(function(){

    const $loginForm = $("#loginForm");

    if($loginForm.length){

        $loginForm.validate({

            rules: {
                email: {
                    required: true
                },
                password: {
                    required: true
                }
            },

            messages: {
                email: {
                    required: 'Please enter your email address'
                },
                password: {
                    required: 'Please enter your password'
                }
            },
            errorElement: 'em',
            errorPlacement: function(error, element){
                error.addClass('help-block');
                error.insertAfter(element);
            }


        })

    }



})