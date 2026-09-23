document.addEventListener('DOMContentLoaded', () => {
    // Filter Buttons Toggle
    const filterBtns = document.querySelectorAll('.filter-btn');
    filterBtns.forEach(btn => {
        btn.addEventListener('click', () => {
            filterBtns.forEach(b => b.classList.remove('active'));
            btn.classList.add('active');
        });
    });

    // Theme Toggle Elements
    
    document.querySelectorAll(".theme-switch span").forEach(button => {
        button.addEventListener("click", () => {
            document
                .querySelectorAll(".theme-switch span")
                .forEach(item => item.classList.remove("selected"));

            button.classList.add("selected");
        });
    });



    const toggleD = document.getElementById('dark-theme-toggle');
    const toggleL = document.getElementById('light-theme-toggle');

    const appC = document.querySelector('.app-container');
    const bd = document.body;
    const don = document.querySelector('.donut');
    const mod = document.querySelector('.modal');

    toggleD.addEventListener('click', () => {
        appC.classList.toggle('dark-theme');
        bd.classList.toggle('dark-theme');
        don.classList.toggle('dark-theme');
        mod.classList.toggle('dark-theme');
    });

    toggleL.addEventListener('click', () => {
        appC.classList.remove('dark-theme');
        bd.classList.remove('dark-theme');
        don.classList.remove('dark-theme');
        mod.classList.remove('dark-theme');
    });

    

    const checkboxes = document.querySelectorAll('.checkbox-item input');
  checkboxes.forEach(box => {
    box.addEventListener('change', (e) => {
      const text = e.target.parentElement.querySelector('.text');
      if (e.target.checked) {
        text.style.textDecoration = 'line-through';
        text.style.opacity = '0.6';
      } else {
        text.style.textDecoration = 'none';
        text.style.opacity = '1';
      }
    });
  });
});