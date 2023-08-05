$(function(){

    $.validator.addMethod('customEmail', function(value, element){
       return this.optional(element) || /^[a-z0-9]+[\._]?[a-z0-9]+[@]\w+[.]\w{2,3}$/.test(value);
    })

    const $registrationForm = $('#registrationForm');

    if($registrationForm.length){

        $registrationForm.validate({

            rules: {
                name: {
                    required: true,
                    minlength: 3
                },
                email: {
                    required: true,
                    customEmail: true
                },
                password: {
                    required: true,
                    minlength: 5,
                    maxlength: 15
                },
                confirmPassword: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                name: {
                    required: 'Please enter user name',
                    minlength: 'Name should have at least 3 characters!'
                },
                email: {
                    required: 'Please enter the email',
                    customEmail: 'Invalid email address'

                },
                password: {
                    required: 'Please enter the password',
                    minlength: 'Password should be at least 5 characters',
                    maxlength: 'Password should have less than 15 characters'
                },
                confirmPassword: {
                    required: 'Please enter the confirmation password',
                    equalTo: 'Passwords do not match!'
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