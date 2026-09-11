document
    .getElementById("login-form")
    .addEventListener("submit", login);
console.log("Button clicked 1");

async function login() {
    event.preventDefault();
    console.log("Button clicked");

    const response = await fetch(
        "http://localhost:8080/auth/login",
        {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({
                email: document.getElementById("email").value,
                password: document.getElementById("password").value
            })
        }
    );

    const data = await response.json();

    console.log(data);
}