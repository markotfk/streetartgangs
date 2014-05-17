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
            for (var i = data.length - 1; i >= 0; i--) { //from all the gangsters in the list
                var gId = data[i].id;
                var userId = data[i].user;
                var latitude = data[i].latitude;
                var longitude = data[i].longitude;
                var gang = data[i].gang;
                
                var gangster = {
                    "gId": gId,
                    "userId": userId,
                    "latitude": latitude,
                    "longitude": longitude,
                    "gang" : gang
                };
                log("Add userId: " + gangster.userId);
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
    $.ajax(gRoot, {
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
