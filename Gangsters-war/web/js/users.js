var userUrl = "streetartgangs/v1/users"

function getUserData() {
    $.ajax(urlRoot + 'users/', {
        contentType: 'application/json',
        type: 'GET',
        success: function(data, status, jqXHR) {
            // loop through users
            for (var i = data.length - 1; i >= 0; i--) {
                var user = {
                    "id": data[i].id,
                    "username" : data[i].username,
                    "email": data[i].email
                };
                log("Add user: " + user.username);
                sendUserData(user);
            }
        },
        error: function(jqXHR, textStatus, errorString) {
            log('error getData: ' + textStatus + ': ' + errorString);
            $('#gather_data').html('Error: ' + errorString);
        }
    });
}

function sendUserData(data) {
    $.ajax(userUrl, {
        contentType: 'application/json',
        type: 'POST',
        success: function(data, status, jqXHR) {
        },
        error: function(jqXHR, textStatus, errorString) {
            log('error sendData: ' + textStatus + ': ' + errorString);
        },
        data: JSON.stringify(data)
    });
}
