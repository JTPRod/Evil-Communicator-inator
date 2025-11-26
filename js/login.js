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

        const baseUrl = "http://localhost:8080/login";

        try {
            const res = await fetch(baseUrl);
            if (!res.ok) {
                alert('Unable to reach server');
                return;
            }
            const users = await res.json();
            const found = users.find(u => u.username === username && u.password === password);
            if (found) {
                sessionStorage.setItem('username', found.username);
                sessionStorage.setItem('user_id', String(found.userId || 0));
                window.location.href = '../index.html';
            } else {
                alert('Invalid credentials');
            }
        } catch (err) {
            alert('Network error: ' + err);
        }
    });
});
