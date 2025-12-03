document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    if (!form) return;
    
    let base = sessionStorage.getItem('serverID');
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const username = document.getElementById('username').value.trim();
        const password = document.getElementById('password').value;

        if (!username || !password) {
            alert('Please enter username and password');
            return;
        }
        let base = sessionStorage.getItem('serverID');
        const payload = { username, password, bio: '' };
        const url = `http://${base}/signup`;
        console.log("Signup URL: " + url);
        try {
            const res = await fetch(url ,{
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload),
            });

            const text = await res.text();

            // backend returns plain text like "User Added" or an error string
            if (res.ok && (text.includes('added') || text.includes('success'))) {
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