const messageArea = document.getElementById('message-area');
const messages = document.getElementById('messages');
const sendButton = document.getElementById('send-button');
const messageInput = document.getElementById('message-input');

function appendMessage(user, content) {
    if (!messages) return;
    messages.value += `\n${user}: ${content}`;
}

const ip = "http://localhost:8080"
const baseUrl = `${ip}/message`;

async function fetchAndDisplayMessages(url) {
    const userCache = {};

    const msgResponse = await fetch(`${url}/message`);

    if (!msgResponse.ok) {
        // console.log(`${url}/message`);
        throw new Error(`ERROR! Status: ${msgResponse.status}`);
    }

    const messageList = await msgResponse.json();

    const processingPromises = messageList.map(async (msg) => {
        // console.log(msg);
        const userId = msg.user_id;
        // console.log("userId = " + userId);

        if (!userCache[userId]) {
            console.log("user cache empty !");
            userCache[userId] = fetchUser(url, userId);
            console.log("Added: " + await userCache[userId]);
        }

        try {
            const username = await userCache[userId];
            // console.log(`Found user/message pair: ${username} ${msg.content}`);
            return `${username}: ${msg.content}`;
        } catch (err) {
            console.error(`Couldn't fetch user ${userId}`, err);
            return `Uknown User: ${msg.content}`;
        }
    });

    const lines = await Promise.all(processingPromises);

    messages.value = lines.join('\n');
}

async function fetchUser(url, id) {
    console.log("Checking for user: " + `${url}/login/${id}`);
    // console.log("Checking id: " + `${id}`)
    const response = await fetch(`${url}/login/${id}`, {
        method: 'GET'
    });

    if (!response.ok) {
        console.warn(`User fetch failed for ${id}: ${response.status}`);
        return "Unknown User";
    }

    const text = await response.text();



    if (!text || text.length == 0) {
        console.warn(`Server returned empty body for user ${id}: `);
        return "Anonymous";
    }

    try {
        const foundUsr = JSON.parse(text);
        console.log(`Found user: ${foundUsr}`); 7
        return foundUsr.username || "Anonymous";
    } catch (err) {
        console.error("JSON Parse error:", err);
        return "Error Parsing User";
    }

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
        // console.log("IP:" + ip);
        // console.log("baseURL:" + baseUrl);
        const res = await fetch(baseUrl, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload)
        });

        const text = await res.text();
        // console.log(text);
        if (res.ok) {
            fetchAndDisplayMessages(ip);
            messageInput.value = '';
        } else {
            alert('Failed to send message: ' + text);
        }
    } catch (err) {
        alert('Network error: ' + err);
    }
});