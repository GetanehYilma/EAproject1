
function saveUserData(){

    $('#success').html("");
    $('#success').append( '<h1>Congratulations! <h1>');
    $.ajax({
        url: '/save',
        type: 'GET',
        contentType: 'application/json',
        success: function(response){
            $('#success').html("");
            $('#success').append( '<h1>Congratulations! <h1>');
            $('#success').append("<h3>Result: " + response + "</h3>");
            $('#success').show();

        },

        error: function(erroObject){

                alert("something went wrong!");

        }

    });
}

