document.addEventListener("DOMContentLoaded", function() {
    // Get a reference to the slider input element
    var slider = document.getElementById("distance");

    slider.addEventListener("input", function() {
        // Get the current value of the slider
        var sliderValue = slider.value;

        // Send the value to the server via an AJAX request
        sendSliderValueToServer(sliderValue);
    });
});

function sendSliderValueToServer(value) {
    // Create an XMLHttpRequest object
    var xhr = new XMLHttpRequest();

    // Define the request method, URL, and whether it's asynchronous
    xhr.open("POST", "/saveSliderValue", true);

    // Set the request header (if needed)
    // xhr.setRequestHeader("Content-Type", "application/json");

    // Define the callback function for when the request is complete
    xhr.onreadystatechange = function() {
        if (xhr.readyState === 4 && xhr.status === 200) {
            // Request was successful, handle any response here (if needed)
            console.log("Slider value saved successfully.");
        }
    };

    // Create a data object to send to the server (if needed)
    var data = {
        value: value
    };

    // Convert the data object to a JSON string (if needed)
    // var jsonData = JSON.stringify(data);

    // Send the request with the data
    xhr.send(data);
}
