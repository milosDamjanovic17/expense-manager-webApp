let validNumber = new RegExp(/^(?!0)\d*\.?\d{0,2}$/); //regexp that will not allow letters, only digits and dot
let lastValid = $("#amount").val(); // bind the amount field from thymeleaf html to JS variable
const validateNumber = function(elem){
    if(validNumber.test(elem.value)){
        lastValid.value = elem.value;
    }else{
        elem.value = lastValid;
    }
}
$(function(){

    $('#createdAt').datepicker({
        dateFormat: "dd/mm/yy",
        changeMonth: true,
        changeYear: true,
        maxDate: new Date()

    })

    const $expenseForm = $("#expenseform");
    if($expenseForm.length){
        $expenseForm.validate({

            rules: {
                name: {
                    required: true, // expense title(*(name)) needs to be populated
                    minlength: 2 // .length needs to be >= 2
                },
                amount: {
                    required: true
                },
                dateString: {
                    required: true
                }
            },
            messages: {
                name: {
                    required: 'Please enter the expense name', // error message for expense title
                    minlength: 'Expense name needs to have more than 2 characters' // error message for minlength
                },
                amount: {
                    required: 'Please enter value for expense amount!'
                },
                dateString: {
                    required: 'Please enter date values'
                }
            }

        })
    }

})