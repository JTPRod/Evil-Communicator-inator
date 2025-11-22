const messageArea = document.getElementById('message-area');
const messages = document.getElementById('messages');
const sendButton = document.getElementById('send-button');
const messageInput = document.getElementById('message-input');

function appendMessage(user, content) {
    if (!messages) return;
    messages.value += `\n${user}: ${content}`;
}

const baseUrl = "http://localhost:8080/message";

function sendMessage(){
    const userId = sessionStorage.getItem('userId') || '0';
    const content = messageInput.value.trim();
    fetch(baseUrl, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ userId, content })
    });
}

sendButton.addEventListener('click', async () => {
    const content = messageInput.value.trim();
    if (!content) return;

    const username = sessionStorage.getItem('username') || 'Anonymous';
    const userId = Number(sessionStorage.getItem('userId') || 0);

    // create message payload compatible with backend Message model
    const payload = {
        content: content,
        userId: userId,
        messageId: 0,
        timestamp: Math.floor(Date.now() / 1000)
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
