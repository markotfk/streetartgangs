//Author     : Marko Karjalainen <markotfk@gmail.com>

var locationsRoot = "streetartgangs/v1/userlocations";

$(document).ready(function() {
    $("#startStopTimerButton").click(toggleTimer);
    //getUserData();
    //getData();
    //toggleTimer();
});

function toggleTimer() {
    if (timerId === -1) {
        startTimer();
    } else {
        stopTimer();
    }
}

function startTimer() {
    timerId = window.setInterval(function() { getData(); }, 60000);
    $("#startStopTimerButton").html("Stop updating locations");
}

function stopTimer() {
    window.clearInterval(timerId);
    timerId = -1;
    $("#startStopTimerButton").html("Start updating locations");
}

function getData() {
    $.ajax(urlRoot + 'gangsters/', {
        contentType: 'application/json',
        type: 'GET',
        success: function(data, status, jqXHR) {
            log('Success Get data');
            for (var i = data.length - 1; i >= 0; i--) { //from all the gangsters in the list
                var id = data[i].id;
                var userId = data[i].user;
                var latitude = data[i].latitude;
                var longitude = data[i].longitude;
                var gang = data[i].gang;
                
                var userlocation = {
                    "id": id,
                    "userId": userId,
                    "latitude": latitude,
                    "longitude": longitude,
                    "gang" : gang
                };
                log("Add location data for userId: " + userlocation.userId);
                sendData(userlocation);
            }
        },
        error: function(jqXHR, textStatus, errorString) {
            stopTimer();
            log('error getData: ' + textStatus + ': ' + errorString);
            $('#gather_data').html('Error: ' + errorString);
        }
    });
}

function sendData(data) {
    $.ajax(locationsRoot, {
        contentType: 'application/json',
        type: 'POST',
        success: function(data, status, jqXHR) {
        },
        error: function(jqXHR, textStatus, errorString) {
            log('error sendData: ' + textStatus + ': ' + errorString);
            stopTimer();
        },
        data: JSON.stringify(data)
    });
}
