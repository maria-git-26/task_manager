document.addEventListener('DOMContentLoaded', () => {
  // Переключение видимости паролей
  const toggleButtons = document.querySelectorAll('.toggle-visibility');

  toggleButtons.forEach(btn => {
    btn.addEventListener('click', () => {
      const targetId = btn.getAttribute('data-target') || 'password';
      const input = document.getElementById(targetId);

      if (input) {
        const isPassword = input.getAttribute('type') === 'password';
        input.setAttribute('type', isPassword ? 'text' : 'password');
        
        // Меняем прозрачность иконки при показе
        btn.style.opacity = isPassword ? '0.6' : '1';
      }
    });
  });
});

document
    .getElementById("register-form")
    .addEventListener("submit", register);
console.log("Button clicked");

async function register() {

  const response = await fetch(
      "http://localhost:8080/auth/register",
      {
        method: "POST",
        headers: {
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          username: document.getElementById("name").value,
          email: document.getElementById("email").value,
          password: document.getElementById("confirm-password").value
        })
      }
  );

  if (response.ok) {
    document.getElementById("message").innerText =
        "Registration successful!";
  } else {
    const error = await response.json();

    document.getElementById("message").innerText =
        error.message;
  }

    if (response.ok) {

        window.location.href =
            "/login/index.html";
    }
}