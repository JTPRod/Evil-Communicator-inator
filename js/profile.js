const editButton = document.getElementById('edit-button');
const bioInput = document.getElementById('bio-input');
const bioDisplay = document.getElementById('bio');
const titleUsername = document.getElementById('titleUsername');
const usernameP = document.getElementById('username');

// Setup UI on load
document.addEventListener('DOMContentLoaded', async () => {
    const username = sessionStorage.getItem('username') || '';
    const userId = sessionStorage.getItem('user_id') || '';
    if (titleUsername)
        titleUsername.textContent = username
            ? `${username} - Doofenshmirtz Evil Inc.`
            : `[Username] - Doofenshmirtz Evil Inc.`;

    if (usernameP)
        usernameP.textContent = `Username: ${username || 'Not logged in'}`;
const payload = {"bio": "", "user_id": ""};
console.log(payload);
let base = sessionStorage.getItem('serverID');

const url = `http://${base}/login`;
console.log("Login URL: " + url);

    // Fetch and display current bio
    try {
         const res = await fetch(url + `/${userId}`, {
                method: 'GET',
                headers: { 'Content-Type': 'application/json' }
        });
        console.log(res);
        if (res.ok) {
            const data = await res.json();
            console.log(data);
            bioDisplay.textContent = `Bio: ${data.bio || ''}`;
        }
    } catch (err) {
        console.error("Failed to fetch bio: " + err);
    }
    // Update bio
    editButton.addEventListener('click', async () => {
        if (!username || !userId) {
            alert('Not logged in');
            return;
        }
        
        const newBio = bioInput.value.trim();
        bioDisplay.textContent = `Bio: ${newBio}`;
        payload.bio = newBio;
        payload.user_id = userId;
        console.log(payload);
        try {
            const putRes = await fetch(url, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(payload)
            });
            alert(await putRes.text());
            if (!putRes.ok) {
                alert("Unable to update profile");
            } else {
                console.log("Profile updated successfully");
            }

            

        } catch (err) {
            alert("Network error: " + err);
        }
    });
});

