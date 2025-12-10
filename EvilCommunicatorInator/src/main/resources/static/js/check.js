document.addEventListener('DOMContentLoaded', () => {

    // Get the server ID from sessionStorage
    let serverID = sessionStorage.getItem('serverID');

    // Check if it's missing, empty, null, or undefined
    if (!serverID || serverID.trim() === "") {
        // Ask user to enter IP + port
        let userInput = prompt("Server ID is not set. Please enter the server IP and port (example: 69.27.21.153:8080):");

        // If the user entered something valid, store it
        if (userInput && userInput.trim() !== "") {
            sessionStorage.setItem('serverID', userInput.trim());
            serverID = userInput.trim();
            alert("Server ID set to: " + serverID);
        } else {
            alert("Server ID still not set. Some features may not work.");
        }
    }
});
