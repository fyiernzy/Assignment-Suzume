// Submit form to servlet on button click
document.getElementById("inputForm").addEventListener("submit", function(event){
	event.preventDefault(); // Prevent default form submission behavior
	const formData = new FormData(this); // Get form data
	const numberInput = formData.get("numberInput"); // Get value from input field
	calculateSumOfDigits(numberInput); // Call servlet function
});

// Call servlet to calculate sum of digits
function calculateSumOfDigits(numberInput) {
    console.log("calculateSumOfDigits called with input: " + numberInput); // Debugging line
	fetch("CalCulateSumServlet?numberInput="+numberInput)
	.then(response => response.text())
	.then(result => {
		document.getElementById("resultDiv").innerHTML = "The sum of digits is: " + result;
	})
	.catch(error => console.log(error));
}

