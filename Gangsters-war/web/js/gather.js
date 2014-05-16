//Author     : Marko Karjalainen <markotfk@gmail.com>

var timerId = 0;
var urlRoot = "http://vm0063.virtues.fi/gangsters/";
var gRoot = "gangs/v1/gangsters";

function log(data) {
    if (data) {
        console.log(data);
    }
}

$(document).ready(function() {

    getData();
    timerId = window.setInterval(function() { getData(); }, 60000);
});

function getData() {
    $.ajax(urlRoot, {
        contentType: 'application/json',
        type: 'GET',
        success: function(data, status, jqXHR) {
            log('Success Get data');
            $('#gather_data').html(data);
            for (var i = data.length - 1; i >= 0; i--) { //from all the gangsters in the list
                var gId = data[i].id;
                var userId = data[i].user;
                var username = data[i].username;
                var latitude = data[i].latitude;
                var longitude = data[i].longitude;
                var tags_created = data[i].tags_created;
                var tags_deleted = data[i].tags_deleted;
                var gang = data[i].gang;
                var color = data[i].color;
                var gangster = {
                    "gId": gId,
                    "userId": userId,
                    "userName": username,
                    "latitude": latitude,
                    "longitude": longitude,
                    "tags_created": tags_created,
                    "tags_deleted": tags_deleted,
                    "gang" : gang,
                    "color": color
                };
                log("Add username: " + gangster.userName);
                log("color: " + gangster.color);
                sendData(gangster);
            }
        },
        error: function(jqXHR, textStatus, errorString) {
            log('error getData: ' + textStatus + ': ' + errorString);
            $('#gather_data').html('Error: ' + errorString);
        }
    });
}

function sendData(data) {
    log("sendData to " + gRoot);
    $.ajax(gRoot, {
        contentType: 'application/json',
        type: 'POST',
        success: function(data, status, jqXHR) {
            log('Success Send data');
        },
        error: function(jqXHR, textStatus, errorString) {
            log('error sendData: ' + textStatus + ': ' + errorString);
        },
        data: JSON.stringify(data)
    });
}
