window.onload = () => {
    loadTasks();
};

async function loadTasks() {

    const token =
        localStorage.getItem("token");

    const response = await fetch(
        "/tasks/getTasks",
        {
            headers: {
                Authorization:
                    "Bearer " + token
            }
        }
    );

    const tasks =
        await response.json();

    renderTasks(tasks);
}

function renderTasks(tasks) {
    console.log((tasks));
    const list =
        document.getElementById("taskList");

    list.innerHTML = "";

    tasks.forEach(task => {

        const li =
            document.createElement("li");

        li.textContent =
            task.title;

        list.appendChild(li);
    });
}

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
    const date = new Date();
    const year = date.getFullYear();
    const month = date.getMonth();
    const now = new Date();
    const Hmonth = new Intl.DateTimeFormat('ru-RU', { month: 'long' }).format(now);

    const today = new Date().toDateString();
    const monthDay = today.split(' ')[2];
    console.log(monthDay);


        const capitalizedMonth = Hmonth.charAt(0).toUpperCase() + Hmonth.slice(1);
        
        // Получаем год (например, "2026")
        const Hyear = now.getFullYear();
        
        // Выводим на страницу
        document.getElementById('current-date').textContent = `${capitalizedMonth}   ${Hyear}`;

    // Первый день месяца и общее количество дней
    const firstDayIndex = (new Date(year, month, 1).getDay() + 6) % 7; 
    const totalDays = new Date(year, month + 1, 0).getDate();

    const container = document.getElementById('month-id');
    let fragment = '';

    // Пустые ячейки для дней недели до начала месяца
    for (let i = 0; i < firstDayIndex; i++) {
    fragment += '<div class="day empty"></div>';
    }

    // Заполнение днями месяца
    for (let i = 1; i <= totalDays; i++) {
        if(monthDay === i.toString()){
            fragment += `<div class="day-today">${i}</div>`;
        }
        else{
            fragment += `<div class="day">${i}</div>`;
        }
    
    }

    container.innerHTML = fragment;

    // Calendar Day Selector
    const days = document.querySelectorAll('.days-grid span:not(.muted)');
    days.forEach(day => {
        day.addEventListener('click', () => {
            days.forEach(d => d.classList.remove('active-day'));
            day.classList.add('active-day');
        });
    });

    document.querySelectorAll(".theme-switch span").forEach(button => {
        button.addEventListener("click", () => {
            document
                .querySelectorAll(".theme-switch span")
                .forEach(item => item.classList.remove("selected"));

            button.classList.add("selected");
        });
    });


const modal = document.getElementById("modal");
const openBtn = document.getElementById("openModal");
const closeBtn = document.getElementById("closeModal");
const cancelBtn = document.getElementById("cancelBtn");

openBtn.addEventListener("click", () => {
    modal.classList.add("active");
});

closeBtn.addEventListener("click", () => {
    modal.classList.remove("active");
});

cancelBtn.addEventListener("click", () => {
    modal.classList.remove("active");
});

modal.addEventListener("click", (e) => {
    if (e.target === modal) {
        modal.classList.remove("active");
    }
});

document
    .getElementById("taskForm")
    .addEventListener("submit", async (e) => {
        e.preventDefault();

        const task = {
            title: document.getElementById("title").value,
            description: document.getElementById("description").value,
            project: document.getElementById("project").value,
            assignee: document.getElementById("assignee").value,
            deadline: document.getElementById("deadline").value,
            priority: document.querySelector(
                'input[name="priority"]:checked'
            ).value
        };
        const token =
            localStorage.getItem("token");
        const response = await fetch(

            "http://localhost:8080/tasks",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    Authorization:
                        "Bearer " + token
                },
                body: JSON.stringify({
                    title: task.title,
                    description: task.description,
                    priority: task.priority,
                    status: "in motion"
                })
            }
        );

        console.log(task);

        alert("Задача создана!");

        modal.classList.remove("active");
        e.target.reset();
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


async function tasksList() {


    window.location.href =
        "/list_final/kanban.html";
}