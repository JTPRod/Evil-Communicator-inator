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

        const url = "http://localhost:8080/login/auth";
        const payload = { username, password };

        try {
            const res = await fetch(url, {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(payload)
            });

            if (!res.ok) {
                alert("Unable to reach server");
                return;
            }

            const data = await res.json();
            
            // The API ALWAYS returns { message, status, maybe user_id }
            if (data.status === "success") {
                // store user_id for persistent login
                sessionStorage.setItem("user_id", data.user_id);
                sessionStorage.setItem("username", username);

                alert(data.message);

                window.location.href = "../index.html";
            } else {
                // signup/login failure
                alert(data.message);
            }

        } catch (err) {
            alert("Network error: " + err);
        }
    });
});
