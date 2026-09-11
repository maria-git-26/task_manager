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