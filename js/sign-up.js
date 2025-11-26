document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value;

        if (!username || !password) {
            alert('Please enter username and password');
            return;
        }

        const payload = { username, password, user_id: 0, bio: '' };
        const baseUrl = "http://localhost:8080/signup";
        
        try {
            const res = await fetch(baseUrl, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload),
            });

            const text = await res.text();

            // backend returns plain text like "User Added" or an error string
            if (res.ok && (text.includes('Added') || text.length === 0)) {
                sessionStorage.setItem('username', username);
                // we don't know backend-assigned id; store 0 as placeholder
                sessionStorage.setItem('user_id', '0');
                window.location.href = '../html/success.html';
            } else {
                alert('Signup failed: ' + text);
            }
        } catch (err) {
            alert('Network error: ' + err);
        }
    });
});