const messageArea = document.getElementById('message-area');
const messages = document.getElementById('messages');
const sendButton = document.getElementById('send-button');
const messageInput = document.getElementById('message-input');

function appendMessage(user, content) {
    if (!messages) return;
    messages.value += `\n${user}: ${content}`;
}

const baseUrl = "http://10.116.4.201:8080/message";

function sendMessage(){
    const user_id = sessionStorage.getItem('user_id') || '0000000000000000000000000';
    const content = messageInput.value.trim();
    const payload = {
        user_id: user_id,
        content: content,
    };
    fetch(baseUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(payload)
    });
}

sendButton.addEventListener('click', async () => {
    const content = messageInput.value.trim();
    if (!content) return;

    const username = sessionStorage.getItem('username') || 'Anonymous';
    //const user_id = Number(sessionStorage.getItem('user_id') || 000000000000000000000000);

    // create message payload compatible with backend Message model
    const user_id = sessionStorage.getItem('user_id') || '000000000000000000000000';
    const payload = {
        user_id: user_id,
        content: content,
    };

    try {
        const res = await fetch(baseUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        const text = await res.text();
        if (res.ok) {
            appendMessage(username, content);
            messageInput.value = '';
        } else {
            alert('Failed to send message: ' + text);
        }
    } catch (err) {
        alert('Network error: ' + err);
    }
});