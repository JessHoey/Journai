// Get the range slider element
const slider = document.getElementById("range-slider");

// Get the element to display the current value
const valueDisplay = document.getElementById("slider-value");

// Set the initial value display
valueDisplay.textContent = slider.value;

// Add an event listener for the slider input event
slider.addEventListener("input", () => {
  // Update the value display with the current slider value
  valueDisplay.textContent = slider.value;
});