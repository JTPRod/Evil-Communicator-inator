    const editButton = document.getElementById('edit-button');
    const bioInput = document.getElementById('bio-input');
    const bioDisplay = document.getElementById('bio');
    const titleUsername = document.getElementById('titleUsername');
    const usernameP = document.getElementById('username');

    document.addEventListener('DOMContentLoaded', async () => {
        const username = sessionStorage.getItem('username') || '';
        const user_id = Number(sessionStorage.getItem('user_id') || 0);

        if (titleUsername) titleUsername.textContent = (username ? username + ' - Doofenshmirtz Evil Inc.' : '[Username] - Doofenshmirtz Evil Inc.');
        if (usernameP) usernameP.textContent = 'Username: ' + (username || 'Not logged in');

        // try to fetch existing bio from backend
        if (user_id > 0) {
            try {
                const res = await fetch('/user');q
                if (res.ok) {
                    const users = await res.json();
                    const me = users.find(u => u.user_id === user_id || u.username === username);
                    if (me && me.bio) {
                        bioDisplay.textContent = 'Bio: ' + me.bio;
                        bioInput.value = me.bio;
                    }
                }
            } catch (err) {
                // ignore fetch errors for profile
            }
        }
    });

    if (editButton) {
        editButton.addEventListener('click', async () => {
            const newBio = bioInput.value;
            if (bioDisplay) bioDisplay.textContent = 'Bio: ' + newBio;

            const username = sessionStorage.getItem('username') || '';
            const user_id = Number(sessionStorage.getItem('user_id') || 0);
            if (!username) {
                alert('Not logged in');
                return;
            }

            try {
                // fetch current user object to preserve password
                const res = await fetch('/user');
                if (!res.ok) {
                    alert('Unable to update profile');
                    return;
                }
                const users = await res.json();
                const me = users.find(u => u.user_id === user_id || u.username === username);
                if (!me) {
                    alert('User not found on server');
                    return;
                }

                me.bio = newBio;

                const putRes = await fetch('/user', {
                    method: 'PUT',
                    headers: { 'Content-Type': 'application/json' },
                    body: JSON.stringify(me)
                });

                if (!putRes.ok) {
                    const t = await putRes.text();
                    alert('Update failed: ' + t);
                }
            } catch (err) {
                alert('Network error: ' + err);
            }
        });
    }
